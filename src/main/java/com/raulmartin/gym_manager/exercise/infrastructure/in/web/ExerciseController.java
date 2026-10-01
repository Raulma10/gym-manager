package com.raulmartin.gym_manager.exercise.infrastructure.in.web;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raulmartin.gym_manager.exercise.domain.model.Exercise;
import com.raulmartin.gym_manager.exercise.domain.model.MuscleGroup;
import com.raulmartin.gym_manager.exercise.domain.port.in.CreateExerciseUseCase;
import com.raulmartin.gym_manager.exercise.domain.port.in.DeleteExerciseUseCase;
import com.raulmartin.gym_manager.exercise.domain.port.in.FindExerciseByIdUseCase;
import com.raulmartin.gym_manager.exercise.domain.port.in.ListExercisesUseCase;
import com.raulmartin.gym_manager.exercise.domain.port.in.SearchExerciseUseCase;
import com.raulmartin.gym_manager.exercise.domain.port.in.UpdateExerciseUseCase;
import com.raulmartin.gym_manager.exercise.infrastructure.in.web.dto.CreateExerciseRequest;
import com.raulmartin.gym_manager.exercise.infrastructure.in.web.dto.ExerciseResponse;
import com.raulmartin.gym_manager.exercise.infrastructure.in.web.dto.UpdateExerciseRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/exercises")
@RequiredArgsConstructor 
public class ExerciseController {

    private final ExerciseMapper exerciseMapper;
    
    private final CreateExerciseUseCase createExerciseUseCase;
    private final UpdateExerciseUseCase updateExerciseUseCase;
    private final FindExerciseByIdUseCase findExerciseByIdUseCase;
    private final ListExercisesUseCase listExercisesUseCase;
    private final SearchExerciseUseCase searchExerciseUseCase;
    private final DeleteExerciseUseCase deleteExerciseUseCase;

    @PostMapping
    public ResponseEntity<ExerciseResponse> createExercise(@Valid  @RequestBody  CreateExerciseRequest createExerciseRequest){
        Exercise exercise = createExerciseUseCase.createExercise(
            createExerciseRequest.name(),
            createExerciseRequest.description(),
            createExerciseRequest.muscleGroup());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(exerciseMapper.toResponse(exercise));
    }

    @GetMapping
    public ResponseEntity<List<ExerciseResponse>> listExercises(){
        List<ExerciseResponse> exercises = listExercisesUseCase.listAll()
            .stream()
            .map(exerciseMapper::toResponse)
            .toList();

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(exercises);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ExerciseResponse>> searchExercises(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) MuscleGroup muscleGroup,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){

        Pageable pageable = PageRequest.of(page, size);

        Page<ExerciseResponse> exercises = searchExerciseUseCase.searchExercise(
            name, 
            muscleGroup, 
            pageable)
            .map(exerciseMapper::toResponse);
        
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseResponse> findExerciseById(@PathVariable UUID id){
        return findExerciseByIdUseCase.findById(id)
                .map(exerciseMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                                    .status(HttpStatus.NOT_FOUND)
                                    .build());
    }


    @PatchMapping("/{id}")
    public ResponseEntity<ExerciseResponse> updateExercise(@PathVariable UUID id, @Valid  @RequestBody  UpdateExerciseRequest updateExerciseRequest){
        Exercise exercise = updateExerciseUseCase.updateExercise(
            id,
            updateExerciseRequest.name(),
            updateExerciseRequest.description(),
            updateExerciseRequest.muscleGroup());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(exerciseMapper.toResponse(exercise));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable UUID id){
        deleteExerciseUseCase.deleteExercise(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}

package com.raulmartin.gym_manager.workout.infrastructure.in.web;

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

import com.raulmartin.gym_manager.workout.domain.model.Objective;
import com.raulmartin.gym_manager.workout.domain.model.Workout;
import com.raulmartin.gym_manager.workout.domain.port.in.AddWorkoutExerciseUseCase;
import com.raulmartin.gym_manager.workout.domain.port.in.CreateWorkoutUseCase;
import com.raulmartin.gym_manager.workout.domain.port.in.DeleteWorkoutUseCase;
import com.raulmartin.gym_manager.workout.domain.port.in.ExerciseSelection;
import com.raulmartin.gym_manager.workout.domain.port.in.FindWorkoutByIdUseCase;
import com.raulmartin.gym_manager.workout.domain.port.in.ListWorkoutUseCase;
import com.raulmartin.gym_manager.workout.domain.port.in.RemoveWorkoutExerciseUseCase;
import com.raulmartin.gym_manager.workout.domain.port.in.SearchWorkoutUseCase;
import com.raulmartin.gym_manager.workout.domain.port.in.UpdateWorkoutExerciseUseCase;
import com.raulmartin.gym_manager.workout.domain.port.in.UpdateWorkoutUseCase;
import com.raulmartin.gym_manager.workout.infrastructure.in.WorkoutMapper;
import com.raulmartin.gym_manager.workout.infrastructure.in.web.dto.AddWorkoutExerciseRequest;
import com.raulmartin.gym_manager.workout.infrastructure.in.web.dto.CreateWorkoutRequest;
import com.raulmartin.gym_manager.workout.infrastructure.in.web.dto.UpdateWorkoutExerciseRequest;
import com.raulmartin.gym_manager.workout.infrastructure.in.web.dto.UpdateWorkoutRequest;
import com.raulmartin.gym_manager.workout.infrastructure.in.web.dto.WorkoutResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/workouts") 
@RequiredArgsConstructor 
public class WorkoutController {

    private final WorkoutMapper workoutMapper;
    private final CreateWorkoutUseCase createWorkoutUseCase;
    private final ListWorkoutUseCase listWorkoutUseCase;
    private final SearchWorkoutUseCase searchWorkoutUseCase;
    private final FindWorkoutByIdUseCase findWorkoutByIdUseCase;
    private final UpdateWorkoutUseCase updateWorkoutUseCase;
    private final AddWorkoutExerciseUseCase addWorkoutExerciseUseCase;
    private final UpdateWorkoutExerciseUseCase updateWorkoutExerciseUseCase;
    private final RemoveWorkoutExerciseUseCase removeWorkoutExerciseUseCase;
    private final DeleteWorkoutUseCase deleteWorkoutUseCase;

    @PostMapping 
    public ResponseEntity<WorkoutResponse> createWorkout(@Valid @RequestBody CreateWorkoutRequest createWorkoutRequest){
        
        List<ExerciseSelection> exercises = createWorkoutRequest.exercises()
        .stream()
        .map(exercise -> new ExerciseSelection(
                exercise.exerciseId(),
                exercise.sets(),
                exercise.reps(),
                exercise.restSeconds(),
                exercise.order()
        ))
        .toList();
        
        Workout workout = createWorkoutUseCase.createWorkout(
            createWorkoutRequest.name(),
            createWorkoutRequest.description(),
            createWorkoutRequest.objective(),
            exercises);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(workoutMapper.toWorkoutResponse(workout));
    }

    @GetMapping("/all")
    public ResponseEntity<List<WorkoutResponse>> listWorkout(){
        List<WorkoutResponse> workouts = listWorkoutUseCase.listAll()
            .stream()
            .map(workoutMapper::toWorkoutResponse)
            .toList();
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(workouts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutResponse> findWorkoutById(@PathVariable UUID id){
        return findWorkoutByIdUseCase.findById(id)
            .map(workoutMapper::toWorkoutResponse)
            .map(ResponseEntity::ok)
            .orElseGet(()-> ResponseEntity
                        .status(HttpStatus.OK)
                        .build());  
    }

    @GetMapping 
    public ResponseEntity<Page<WorkoutResponse>> searchWorkout(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Objective objective,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page,size);

        Page<WorkoutResponse> workouts = searchWorkoutUseCase.searchWorkout(
            name, 
            objective, 
            pageable)
            .map(workoutMapper::toWorkoutResponse);
        return ResponseEntity.ok(workouts);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<WorkoutResponse> updateWorkout(@PathVariable UUID id, @Valid @RequestBody UpdateWorkoutRequest request) {
        Workout updated = updateWorkoutUseCase.updateWorkout(
                id,
                request.name(),
                request.description(),
                request.objective()
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(workoutMapper.toWorkoutResponse(updated));
    }

    @PostMapping("/{id}/exercises")
    public ResponseEntity<WorkoutResponse> addExercise(@PathVariable UUID id, @Valid @RequestBody AddWorkoutExerciseRequest request) {
        Workout updated = addWorkoutExerciseUseCase.addExercise(
                id,
                request.exerciseId(),
                request.sets(),
                request.reps(),
                request.restSeconds(),
                request.order()
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(workoutMapper.toWorkoutResponse(updated));
    }

    @PatchMapping("/{id}/exercises/{exerciseId}")
    public ResponseEntity<WorkoutResponse> updateExercise(@PathVariable UUID id,
                                                            @PathVariable UUID exerciseId,
                                                            @Valid @RequestBody UpdateWorkoutExerciseRequest request) {
        Workout updated = updateWorkoutExerciseUseCase.updateExercise(
                id,
                exerciseId,
                request.sets(),
                request.reps(),
                request.restSeconds(),
                request.order()
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(workoutMapper.toWorkoutResponse(updated));
    }
 
    @DeleteMapping("/{id}/exercises/{exerciseId}")
    public ResponseEntity<WorkoutResponse> removeExercise(@PathVariable UUID id, @PathVariable UUID exerciseId) {
        Workout updated = removeWorkoutExerciseUseCase.removeExercise(id, exerciseId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(workoutMapper.toWorkoutResponse(updated));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable UUID id){
        deleteWorkoutUseCase.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}

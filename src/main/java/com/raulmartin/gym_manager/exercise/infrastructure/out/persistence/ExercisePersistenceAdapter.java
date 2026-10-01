package com.raulmartin.gym_manager.exercise.infrastructure.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.raulmartin.gym_manager.exercise.domain.model.Exercise;
import com.raulmartin.gym_manager.exercise.domain.model.MuscleGroup;
import com.raulmartin.gym_manager.exercise.domain.port.out.ExerciseRepositoryPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class ExercisePersistenceAdapter implements ExerciseRepositoryPort{

    private final ExerciseJpaRepository exerciseJpaRepository;
    private final ExercisePersistenceMapper exercisePersistenceMapper;
    private final MuscleGroupPersistenceMapper muscleGroupPersistenceMapper;
    
    @Override
    public Exercise save(Exercise exercise) {
        ExerciseJpaEntity exerciseEntity = exercisePersistenceMapper.toEntity(exercise);
        ExerciseJpaEntity saved = exerciseJpaRepository.save(exerciseEntity);
        return exercisePersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Exercise> findById(UUID id) {
        return exerciseJpaRepository
                .findById(id)
                .map(exercisePersistenceMapper::toDomain);
    }

    @Override
    public List<Exercise> findAll() {
        return exerciseJpaRepository
                .findAll()
                .stream()
                .map(exercisePersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        exerciseJpaRepository.deleteById(id);
    }

    @Override
    public Page<Exercise> searchExercise(String name, MuscleGroup muscleGroup,Pageable pageable) {
        MuscleGroupJpa muscleGroupJpa = null;

        if (muscleGroup != null) {
            muscleGroupJpa = muscleGroupPersistenceMapper
                    .toEntityMuscleGroupJpa(muscleGroup);
        }
        
        return exerciseJpaRepository
            .searchExercise(
                    name,
                    muscleGroupJpa,
                    pageable
            )
            .map(exercisePersistenceMapper::toDomain);
    }
    
}

package com.raulmartin.gym_manager.workout.infrastructure.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.raulmartin.gym_manager.workout.domain.model.Objective;
import com.raulmartin.gym_manager.workout.domain.model.Workout;
import com.raulmartin.gym_manager.workout.domain.model.WorkoutExercise;
import com.raulmartin.gym_manager.workout.domain.port.out.WorkoutRepositoryPort;
import com.raulmartin.gym_manager.workout.infrastructure.out.persistence.mapper.ObjectivePersistenceMapper;
import com.raulmartin.gym_manager.workout.infrastructure.out.persistence.mapper.WorkoutExercisePersistenceMapper;
import com.raulmartin.gym_manager.workout.infrastructure.out.persistence.mapper.WorkoutPersistenceMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor 
public class WorkoutPersistenceAdapter implements WorkoutRepositoryPort{
    
    private final WorkoutJpaRepository workoutJpaRepository;
    private final WorkoutPersistenceMapper workoutPersistenceMapper;
    private final WorkoutExercisePersistenceMapper workoutExercisePersistenceMapper;
    private final ObjectivePersistenceMapper objectivePersistenceMapper;
    
    @Override
    @Transactional 
    public Workout save(Workout workout) {

        WorkoutJpaEntity entity = workoutJpaRepository.findById(workout.getId())
                .orElseGet(() -> workoutPersistenceMapper.toEntity(workout));

        entity.setName(workout.getName());
        entity.setDescription(workout.getDescription());
        entity.setObjective(
                objectivePersistenceMapper.toEntity(workout.getObjective())
        );

        if (entity.getWorkoutExercises() != null) {

            entity.getWorkoutExercises().removeIf(jpaExercise ->
                    workout.getWorkoutExercises()
                            .stream()
                            .noneMatch(domainExercise ->
                                    domainExercise.getId()
                                            .equals(jpaExercise.getId())
                            )
            );

            for (WorkoutExercise domainExercise : workout.getWorkoutExercises()) {

                Optional<WorkoutExerciseJpaEntity> existingExercise =
                        entity.getWorkoutExercises()
                                .stream()
                                .filter(jpaExercise ->
                                        jpaExercise.getId()
                                                .equals(domainExercise.getId())
                                )
                                .findFirst();

                if (existingExercise.isPresent()) {

                    WorkoutExerciseJpaEntity jpaExercise =
                            existingExercise.get();

                    jpaExercise.setExercise(
                            workoutExercisePersistenceMapper
                                    .toEntity(domainExercise)
                                    .getExercise()
                    );

                    jpaExercise.setSets(domainExercise.getSets());
                    jpaExercise.setReps(domainExercise.getReps());
                    jpaExercise.setRestSeconds(domainExercise.getRestSeconds());
                    jpaExercise.setExerciseOrder(
                            domainExercise.getExerciseOrder()
                    );

                } else {

                    WorkoutExerciseJpaEntity newExercise =
                            workoutExercisePersistenceMapper
                                    .toEntity(domainExercise);

                    newExercise.setWorkout(entity);

                    entity.getWorkoutExercises().add(newExercise);
                }
            }
        }

        WorkoutJpaEntity saved =
                workoutJpaRepository.save(entity);

        return workoutPersistenceMapper.toDomain(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Workout> findById(UUID id) {
        return workoutJpaRepository
                .findById(id)
                .map(workoutPersistenceMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Workout> findAll() {
        return workoutJpaRepository
            .findAll()
            .stream()
            .map(workoutPersistenceMapper::toDomain)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Workout> searchWorkout(String name, Objective objective, Pageable pageable) {

        ObjectiveJpa objectiveJpa = null;
        if(objective != null){
            objectiveJpa = objectivePersistenceMapper.toEntity(objective);
        }

        return workoutJpaRepository.searchWorkout(
            name,
            objectiveJpa, 
            pageable)
            .map(workoutPersistenceMapper::toDomain);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        workoutJpaRepository.deleteById(id);
    }
    
}

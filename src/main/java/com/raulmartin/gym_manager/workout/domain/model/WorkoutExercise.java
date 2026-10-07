package com.raulmartin.gym_manager.workout.domain.model;

import java.util.UUID;

import com.raulmartin.gym_manager.exercise.domain.model.Exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WorkoutExercise {

    private final UUID id;
    private final Exercise exercise;
    private final int sets;
    private final int reps;
    private final int restSeconds;
    private final int exerciseOrder;

    public static WorkoutExercise create(Exercise exercise, int sets, int reps, int restSeconds, int order) {
        validate(exercise, sets, reps, restSeconds, order);
        return new WorkoutExercise(UUID.randomUUID(), exercise, sets, reps, restSeconds, order);
    }

    public WorkoutExercise update(
            Exercise exercise,
            int sets,
            int reps,
            int restSeconds,
            int order) {

        validate(exercise, sets, reps, restSeconds, order);

        return new WorkoutExercise(
                this.id,
                exercise,
                sets,
                reps,
                restSeconds,
                order
        );
    }

    private static void validate(
            Exercise exercise,
            int sets,
            int reps,
            int restSeconds,
            int order) {

        if (exercise == null) {
            throw new IllegalArgumentException("Exercise is required");
        }

        if (sets <= 0) {
            throw new IllegalArgumentException("Sets must be greater than 0");
        }

        if (reps <= 0) {
            throw new IllegalArgumentException("Reps must be greater than 0");
        }

        if (restSeconds < 0) {
            throw new IllegalArgumentException("Rest seconds cannot be negative");
        }

        if (order < 0) {
            throw new IllegalArgumentException("Order cannot be negative");
        }
    }
}
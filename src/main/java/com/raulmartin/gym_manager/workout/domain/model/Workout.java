package com.raulmartin.gym_manager.workout.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Workout {

    private final UUID id;
    private final String name;
    private final String description;
    private final Objective objective;
    private final List<WorkoutExercise> workoutExercises;

    public static Workout create(String name, String description, Objective objective,
                                  List<WorkoutExercise> exercises) {
        validate(name, description, objective, exercises);
        return new Workout(UUID.randomUUID(), name, description, objective, List.copyOf(exercises));
    }
    
    public Workout update(String newName, String newDescription, Objective newObjective) {
        String finalName = (newName != null) ? newName : this.name;
        String finalDescription = (newDescription != null) ? newDescription : this.description;
        Objective finalObjective = (newObjective != null) ? newObjective : this.objective;
 
        if (finalName == null || finalName.isBlank()) {
            throw new IllegalArgumentException("Workout name is required");
        }
        if (finalDescription == null || finalDescription.isBlank()) {
            throw new IllegalArgumentException("Workout description is required");
        }
        if (finalObjective == null) {
            throw new IllegalArgumentException("Workout objective is required");
        }
        return new Workout(this.id, finalName, finalDescription, finalObjective, this.workoutExercises);
    }

    public Workout addExercise(WorkoutExercise newExercise) {
        if (newExercise == null) {
            throw new IllegalArgumentException("Exercise is required");
        }
        boolean alreadyPresent = this.workoutExercises.stream()
                .anyMatch(we -> we.getExercise().getId().equals(newExercise.getExercise().getId()));
        if (alreadyPresent) {
            throw new IllegalArgumentException(
                    "Exercise already in this workout: " + newExercise.getExercise().getId());
        }
 
        List<WorkoutExercise> updated = new ArrayList<>(this.workoutExercises);
        updated.add(newExercise);
        return new Workout(this.id, this.name, this.description, this.objective, List.copyOf(updated));
    }

    public Workout removeExercise(UUID exerciseId) {
        boolean present = this.workoutExercises.stream()
                .anyMatch(we -> we.getExercise().getId().equals(exerciseId));
        if (!present) {
            throw new IllegalArgumentException("Exercise not found in this workout: " + exerciseId);
        }
 
        List<WorkoutExercise> updated = this.workoutExercises.stream()
                .filter(we -> !we.getExercise().getId().equals(exerciseId))
                .toList();
 
        if (updated.isEmpty()) {
            throw new IllegalArgumentException("Cannot remove the last exercise of a workout");
        }
        return new Workout(this.id, this.name, this.description, this.objective, updated);
    }

    public Workout updateExercise(UUID exerciseId, int sets, int reps, int restSeconds, int order) {
        List<WorkoutExercise> updated = new ArrayList<>();
        boolean found = false;
 
        for (WorkoutExercise we : this.workoutExercises) {
            if (we.getExercise().getId().equals(exerciseId)) {
                updated.add(WorkoutExercise.create(we.getExercise(), sets, reps, restSeconds, order));
                found = true;
            } else {
                updated.add(we);
            }
        }
 
        if (!found) {
            throw new IllegalArgumentException("Exercise not found in this workout: " + exerciseId);
        }
        return new Workout(this.id, this.name, this.description, this.objective, List.copyOf(updated));
    }
 
    private static void validate(String name, String description, Objective objective,
                                  List<WorkoutExercise> exercises) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Workout name is required");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Workout description is required");
        }
        if (objective == null) {
            throw new IllegalArgumentException("Workout objective is required");
        }
        if (exercises == null || exercises.isEmpty()) {
            throw new IllegalArgumentException("Workout must have at least one exercise");
        }
    }
}
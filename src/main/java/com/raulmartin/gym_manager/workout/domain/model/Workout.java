package com.raulmartin.gym_manager.workout.domain.model;

import java.util.ArrayList;
import java.util.Comparator;
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
            throw new IllegalArgumentException("El nombre de la tabla es obligatorio");
        }
        if (finalDescription == null || finalDescription.isBlank()) {
            throw new IllegalArgumentException("La descripción de la tabla es obligatoria");
        }
        if (finalObjective == null) {
            throw new IllegalArgumentException("El objetivo es obligatorio");
        }
        return new Workout(this.id, finalName, finalDescription, finalObjective, this.workoutExercises);
    }

    public Workout addExercise(WorkoutExercise newExercise) {
        if (newExercise == null) {
            throw new IllegalArgumentException("El ejercicio es obligatorio");
        }
        boolean alreadyPresent = this.workoutExercises.stream()
                .anyMatch(we -> we.getExercise().getId().equals(newExercise.getExercise().getId()));
        if (alreadyPresent) {
            throw new IllegalArgumentException(
                    "El ejercicio ya se encuentra en la tabla");
        }

        List<WorkoutExercise> updated = new ArrayList<>(this.workoutExercises);
        for (WorkoutExercise workoutExercise : this.workoutExercises) {
            if(workoutExercise.getExerciseOrder() >= newExercise.getExerciseOrder()){
                updated.add(workoutExercise.update(
                    workoutExercise.getExercise(),
                    workoutExercise.getSets(),
                    workoutExercise.getReps(),
                    workoutExercise.getRestSeconds(),
                    workoutExercise.getExerciseOrder() + 1
                ));
            }
        } 
        updated.add(newExercise);
        updated.sort(Comparator.comparingInt(WorkoutExercise::getExerciseOrder));  
        return new Workout(this.id, this.name, this.description, this.objective, List.copyOf(updated));
    }

    public Workout removeExercise(UUID exerciseId) {
        boolean present = this.workoutExercises.stream()
                .anyMatch(we -> we.getExercise().getId().equals(exerciseId));
        if (!present) {
            throw new IllegalArgumentException("No se  ha encontrado el ejercicio");
        }
 
        List<WorkoutExercise> updated = this.workoutExercises.stream()
                .filter(we -> !we.getExercise().getId().equals(exerciseId))
                .toList();
 
        if (updated.isEmpty()) {
            throw new IllegalArgumentException("No se puede eliminar el último ejercicio de un entrenamiento");
        }
        return new Workout(this.id, this.name, this.description, this.objective, updated);
    }

    public Workout updateExercise(UUID exerciseId, int sets, int reps, int restSeconds, int order) {
        WorkoutExercise exerciseToUpdate = this.workoutExercises.stream()
                .filter(we -> we.getExercise().getId().equals(exerciseId))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("No se ha encontrado el ejercicio"));

        int oldOrder = exerciseToUpdate.getExerciseOrder();

        List<WorkoutExercise> updated = new ArrayList<>();

        for (WorkoutExercise workoutExercise : this.workoutExercises) {

            if (workoutExercise.getExercise().getId().equals(exerciseId)) {

                updated.add(workoutExercise.update(
                        workoutExercise.getExercise(),
                        sets,
                        reps,
                        restSeconds,
                        order
                ));

            } else if (order > oldOrder && workoutExercise.getExerciseOrder() > oldOrder && workoutExercise.getExerciseOrder() <= order) {

                updated.add(workoutExercise.update(
                        workoutExercise.getExercise(),
                        workoutExercise.getSets(),
                        workoutExercise.getReps(),
                        workoutExercise.getRestSeconds(),
                        workoutExercise.getExerciseOrder() - 1
                ));

            } else if (order < oldOrder && workoutExercise.getExerciseOrder() >= order && workoutExercise.getExerciseOrder() < oldOrder) {

                updated.add(workoutExercise.update(
                        workoutExercise.getExercise(),
                        workoutExercise.getSets(),
                        workoutExercise.getReps(),
                        workoutExercise.getRestSeconds(),
                        workoutExercise.getExerciseOrder() + 1
                ));

            } else {
                updated.add(workoutExercise);
            }
        }

        updated.sort(Comparator.comparingInt(WorkoutExercise::getExerciseOrder));
        return new Workout(this.id, this.name, this.description, this.objective, List.copyOf(updated));
    }
 
    private static void validate(String name, String description, Objective objective, List<WorkoutExercise> exercises) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre de la tabla es obligatorio");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("La descripción es obligatoria");
        }
        if (objective == null) {
            throw new IllegalArgumentException("El objetivo es obligatorio");
        }
        if (exercises == null || exercises.isEmpty()) {
            throw new IllegalArgumentException("La lista de ejercicios no puede estar vacía");
        }

        boolean duplicatedOrder = exercises.stream()
            .map(WorkoutExercise::getExerciseOrder)
            .distinct()
            .count() != exercises.size();

        if (duplicatedOrder) {
            throw new IllegalArgumentException("No puede haber dos o más ejercicios con el mismo orden");
        }
    }
}
package com.raulmartin.gym_manager.exercise.domain.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class Exercise {
    
    private final UUID id;
    private final String name;
    private final String description;
    private final MuscleGroup muscleGroup;
 
    public static Exercise create(String name, String description, MuscleGroup muscleGroup) {
        validate(name, description, muscleGroup);
        return new Exercise(UUID.randomUUID(), name, description, muscleGroup);
    }

    public Exercise update(String newName, String newDescription, MuscleGroup newMuscleGroup) {
        validate(newName, newDescription, newMuscleGroup);
        return new Exercise(this.id, newName, newDescription, newMuscleGroup);
    }
 
    private static void validate(String name, String description, MuscleGroup muscleGroup) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre del ejercicio es obligatorio");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("La descripción del ejercicio es obligatoria");
        }
        if (muscleGroup == null) {
            throw new IllegalArgumentException("El grupo muscular es obligatorio");
        }
    }
}

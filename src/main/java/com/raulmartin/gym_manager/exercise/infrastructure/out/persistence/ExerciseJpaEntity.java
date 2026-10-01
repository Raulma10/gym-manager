package com.raulmartin.gym_manager.exercise.infrastructure.out.persistence;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "exercises")
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
public class ExerciseJpaEntity{
    
    @Id
    private UUID id;
 
    @NotNull
    @NotBlank
    @Size(min = 2, max = 100)
    @Column(unique = true, nullable = false)
    private String name;
 
    @NotNull
    @NotBlank
    @Size(max = 1000)
    @Column(length = 1000)
    private String description;
 
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MuscleGroupJpa muscleGroup;
}

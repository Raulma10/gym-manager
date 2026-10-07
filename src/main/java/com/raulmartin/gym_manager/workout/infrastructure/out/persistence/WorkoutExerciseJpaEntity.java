package com.raulmartin.gym_manager.workout.infrastructure.out.persistence;
 
import java.util.UUID;
 
import com.raulmartin.gym_manager.exercise.infrastructure.out.persistence.ExerciseJpaEntity;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Entity
@Table(name = "workout_exercises")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutExerciseJpaEntity {
 
    @Id
    private UUID id;
 
    @ManyToOne(optional = false)
    @JoinColumn(name = "workout_id")
    private WorkoutJpaEntity workout;
 
    @ManyToOne(optional = false)
    @JoinColumn(name = "exercise_id")
    private ExerciseJpaEntity exercise;
 
    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Integer sets;
 
    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Integer reps;
 
    @NotNull
    @Min(0)
    @Column(nullable = false)
    private Integer restSeconds;
 
    @NotNull
    @Min(0)
    @Column(nullable = false)
    private Integer exerciseOrder;
}
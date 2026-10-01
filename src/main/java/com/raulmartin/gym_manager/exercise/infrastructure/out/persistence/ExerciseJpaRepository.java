package com.raulmartin.gym_manager.exercise.infrastructure.out.persistence;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExerciseJpaRepository extends JpaRepository<ExerciseJpaEntity, UUID>{

    @Query("""
        SELECT e
        FROM ExerciseJpaEntity e
        WHERE (:name IS NULL OR LOWER(e.name) LIKE LOWER(CONCAT('%', CAST(:name AS string), '%')))
        AND (:muscleGroup IS NULL OR e.muscleGroup = :muscleGroup)
        """)
    Page<ExerciseJpaEntity> searchExercise(
        @Param("name") String name,
        @Param("muscleGroup") MuscleGroupJpa muscleGroupJpa,
        Pageable pageable
    );
    
}

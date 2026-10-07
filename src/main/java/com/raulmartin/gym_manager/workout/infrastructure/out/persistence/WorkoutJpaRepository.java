package com.raulmartin.gym_manager.workout.infrastructure.out.persistence;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WorkoutJpaRepository extends JpaRepository<WorkoutJpaEntity,UUID> {
    @Query("""
        SELECT w
        FROM WorkoutJpaEntity w
        WHERE (:name IS NULL OR LOWER(w.name) LIKE LOWER(CONCAT('%', CAST(:name AS string), '%')))
        AND (:objective IS NULL OR w.objective = :objective)
        """)
    Page<WorkoutJpaEntity> searchWorkout(
        @Param("name") String name,
        @Param("objective") ObjectiveJpa objective,
        Pageable pageable
    );
}

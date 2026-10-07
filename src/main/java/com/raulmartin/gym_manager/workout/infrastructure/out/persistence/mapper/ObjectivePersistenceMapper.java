package com.raulmartin.gym_manager.workout.infrastructure.out.persistence.mapper;

import org.mapstruct.Mapper;

import com.raulmartin.gym_manager.workout.domain.model.Objective;
import com.raulmartin.gym_manager.workout.infrastructure.out.persistence.ObjectiveJpa;

@Mapper(componentModel = "spring")
public interface ObjectivePersistenceMapper {
    Objective toDomain(ObjectiveJpa objectiveJpa);
    ObjectiveJpa toEntity(Objective objective);
}

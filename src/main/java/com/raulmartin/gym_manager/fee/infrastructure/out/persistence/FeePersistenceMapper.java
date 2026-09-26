package com.raulmartin.gym_manager.fee.infrastructure.out.persistence;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.raulmartin.gym_manager.fee.domain.model.Fee;

@Mapper(componentModel = "spring", uses = FeeStatusPersistenceMapper.class)
public interface FeePersistenceMapper {

    FeeJpaEntity toEntity(Fee fee);

    @Mapping(target = "changeStatus", ignore = true)
    Fee toDomain(FeeJpaEntity entity);
}
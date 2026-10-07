package com.raulmartin.gym_manager.fee.infrastructure.out.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.raulmartin.gym_manager.fee.domain.model.Fee;
import com.raulmartin.gym_manager.fee.infrastructure.out.persistence.FeeJpaEntity;

@Mapper(componentModel = "spring", uses = FeeStatusPersistenceMapper.class)
public interface FeePersistenceMapper {

    FeeJpaEntity toEntity(Fee fee);

    @Mapping(target = "changeStatus", ignore = true)
    Fee toDomain(FeeJpaEntity entity);
}
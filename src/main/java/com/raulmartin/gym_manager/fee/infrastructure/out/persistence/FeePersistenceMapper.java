package com.raulmartin.gym_manager.fee.infrastructure.out.persistence;

import org.mapstruct.Mapper;

import com.raulmartin.gym_manager.fee.domain.model.Fee;

@Mapper(componentModel = "spring")
public interface FeePersistenceMapper {

    FeeJpaEntity toEntity(Fee fee);

    Fee toDomain(FeeJpaEntity entity);
    
}

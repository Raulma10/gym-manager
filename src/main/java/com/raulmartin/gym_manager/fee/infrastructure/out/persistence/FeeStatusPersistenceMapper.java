package com.raulmartin.gym_manager.fee.infrastructure.out.persistence;

import org.mapstruct.Mapper;

import com.raulmartin.gym_manager.fee.domain.model.FeeStatus;

@Mapper(componentModel = "spring")
public interface FeeStatusPersistenceMapper {
    FeeStatusJpa toEntityFeeStatusJpa(FeeStatus feeStatus);
    FeeStatus toDomainFeeStatus(FeeStatusJpa feeStatusJpa); 
}

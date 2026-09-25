package com.raulmartin.gym_manager.fee.infrastructure.in.web;

import org.mapstruct.Mapper;

import com.raulmartin.gym_manager.fee.domain.model.Fee;
import com.raulmartin.gym_manager.fee.infrastructure.in.web.dto.FeeResponse;

@Mapper(componentModel = "spring")
public interface FeeMapper {

    FeeResponse toResponse(Fee fee);
}
package com.raulmartin.gym_manager.fee.application.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.fee.domain.model.Fee;
import com.raulmartin.gym_manager.fee.domain.port.in.FindFeeByIdUseCase;
import com.raulmartin.gym_manager.fee.domain.port.out.FeeRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindFeeByIdService implements FindFeeByIdUseCase{

    private final FeeRepositoryPort feeRepositoryPort;

    @Override
    public Optional<Fee> findById(UUID id) {
        return feeRepositoryPort.findById(id);
    }
    
}

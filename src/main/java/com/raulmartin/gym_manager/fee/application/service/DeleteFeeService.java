package com.raulmartin.gym_manager.fee.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.fee.domain.port.in.DeleteFeeUseCase;
import com.raulmartin.gym_manager.fee.domain.port.out.FeeRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class DeleteFeeService implements DeleteFeeUseCase{

    private final FeeRepositoryPort feeRepositoryPort;
    
    @Override
    public void deleteFee(UUID id) {
        feeRepositoryPort.findById(id).orElseThrow(() -> new IllegalArgumentException("No se ha encontrado la cuota"));
        feeRepositoryPort.deleteById(id);
    }
    
}

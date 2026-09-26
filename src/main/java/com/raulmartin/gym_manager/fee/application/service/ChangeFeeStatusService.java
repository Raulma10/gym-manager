package com.raulmartin.gym_manager.fee.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.fee.domain.model.Fee;
import com.raulmartin.gym_manager.fee.domain.port.in.ChangeFeeStatusUseCase;
import com.raulmartin.gym_manager.fee.domain.port.out.FeeRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ChangeFeeStatusService implements ChangeFeeStatusUseCase{

    private final FeeRepositoryPort feeRepositoryPort;

    @Override
    public Fee changeFeeStatus(UUID id) {
        Fee fee = feeRepositoryPort.findById(id).orElseThrow(() -> new IllegalArgumentException("No se ha encontrado la cuota"));
        Fee updatedFee = fee.changeStatus(fee.getFeeStatus());
        return feeRepositoryPort.save(updatedFee);
    }
    
}

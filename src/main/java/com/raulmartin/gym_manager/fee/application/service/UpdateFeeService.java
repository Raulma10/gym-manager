package com.raulmartin.gym_manager.fee.application.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.fee.domain.model.Fee;
import com.raulmartin.gym_manager.fee.domain.port.in.UpdateFeeUseCase;
import com.raulmartin.gym_manager.fee.domain.port.out.FeeRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UpdateFeeService implements UpdateFeeUseCase{

    private final FeeRepositoryPort feeRepositoryPort;

    @Override
    public Fee updateFee(UUID id, String name, BigDecimal price, int weeklySessions) {
        Fee fee = feeRepositoryPort.findById(id).orElseThrow(() -> new IllegalArgumentException("No se ha encontrado la cuota"));
        Fee updatedFee = fee.update(name, price, weeklySessions);
        return feeRepositoryPort.save(updatedFee);
    }
    
}

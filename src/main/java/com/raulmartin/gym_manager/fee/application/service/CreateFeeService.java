package com.raulmartin.gym_manager.fee.application.service;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.fee.domain.model.Fee;
import com.raulmartin.gym_manager.fee.domain.port.in.CreateFeeUseCase;
import com.raulmartin.gym_manager.fee.domain.port.out.FeeRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateFeeService implements CreateFeeUseCase{

    private final FeeRepositoryPort feeRepositoryPort;

    @Override
    public Fee createFee(String name, BigDecimal price, int weeklySessions) {
        Fee fee = Fee.create(name, price, weeklySessions);
        return feeRepositoryPort.save(fee);
    }
    
}

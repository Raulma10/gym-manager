package com.raulmartin.gym_manager.fee.application.service;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.fee.domain.model.Fee;
import com.raulmartin.gym_manager.fee.domain.model.FeeStatus;
import com.raulmartin.gym_manager.fee.domain.port.in.SearchFeesUseCase;
import com.raulmartin.gym_manager.fee.domain.port.out.FeeRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchFeesService implements SearchFeesUseCase{

    private final FeeRepositoryPort feeRepositoryPort;

    @Override
    public Page<Fee> searchFee(String name, BigDecimal price, Integer weeklySessions, FeeStatus status, Pageable pageable) {
        return feeRepositoryPort.searchFee(name, price, weeklySessions, status, pageable);
    }
    
}

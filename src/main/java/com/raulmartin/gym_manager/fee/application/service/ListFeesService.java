package com.raulmartin.gym_manager.fee.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.fee.domain.model.Fee;
import com.raulmartin.gym_manager.fee.domain.port.in.ListFeesUseCase;
import com.raulmartin.gym_manager.fee.domain.port.out.FeeRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ListFeesService implements ListFeesUseCase{

    private final FeeRepositoryPort feeRepositoryPort;

    @Override
    public List<Fee> listAll() {
        return feeRepositoryPort.findAll();
    }
    
}

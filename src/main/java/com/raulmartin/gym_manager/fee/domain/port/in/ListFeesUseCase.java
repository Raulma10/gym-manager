package com.raulmartin.gym_manager.fee.domain.port.in;

import java.util.List;

import com.raulmartin.gym_manager.fee.domain.model.Fee;

public interface ListFeesUseCase {
    List<Fee> listAll();
}

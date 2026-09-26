package com.raulmartin.gym_manager.fee.domain.port.in;

import java.util.UUID;

import com.raulmartin.gym_manager.fee.domain.model.Fee;

public interface ChangeFeeStatusUseCase {
    Fee changeFeeStatus(UUID id);
}

package com.raulmartin.gym_manager.fee.domain.port.in;

import java.util.UUID;

public interface DeleteFeeUseCase {
    void deleteFee(UUID id);
}

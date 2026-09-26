package com.raulmartin.gym_manager.member.domain.port.in;

import java.time.LocalDate;
import java.util.UUID;

import com.raulmartin.gym_manager.member.domain.model.Member;

public interface CreateMemberUseCase {
    Member create(String name, String lastName, String mail, String phone, LocalDate birthDate, UUID feeId);
}

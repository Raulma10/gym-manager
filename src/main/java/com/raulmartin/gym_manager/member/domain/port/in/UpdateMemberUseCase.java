package com.raulmartin.gym_manager.member.domain.port.in;

import java.time.LocalDate;
import java.util.UUID;

import com.raulmartin.gym_manager.member.domain.model.Member;

public interface UpdateMemberUseCase {
    Member update(UUID id, String name, String lastName, String mail, String phone, LocalDate birthDate);
}

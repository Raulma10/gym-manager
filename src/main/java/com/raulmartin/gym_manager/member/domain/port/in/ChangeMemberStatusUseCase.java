package com.raulmartin.gym_manager.member.domain.port.in;

import java.util.UUID;

import com.raulmartin.gym_manager.member.domain.model.Member;

public interface ChangeMemberStatusUseCase {
    Member changeMemberStatus(UUID id);
}

package com.raulmartin.gym_manager.member.domain.port.in;

import java.util.Optional;
import java.util.UUID;

import com.raulmartin.gym_manager.member.domain.model.Member;

public interface FindMemberByIdUseCase {
    Optional<Member> findById(UUID id);
}

package com.raulmartin.gym_manager.member.domain.port.in;

import java.util.UUID;

public interface DeleteMemberUseCase {
    void deleteMember(UUID id);
}

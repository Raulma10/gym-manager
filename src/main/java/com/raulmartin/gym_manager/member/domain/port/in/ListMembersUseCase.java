package com.raulmartin.gym_manager.member.domain.port.in;

import java.util.List;

import com.raulmartin.gym_manager.member.domain.model.Member;

public interface ListMembersUseCase {
    List<Member> findAll();
}

package com.raulmartin.gym_manager.member.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.member.domain.model.Member;
import com.raulmartin.gym_manager.member.domain.port.in.ListMembersUseCase;
import com.raulmartin.gym_manager.member.domain.port.out.MemberRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ListMembersService implements ListMembersUseCase{

    private final MemberRepositoryPort memberRepositoryPort;

    @Override
    public List<Member> findAll() {
        return memberRepositoryPort.findAll();
    }
    
}

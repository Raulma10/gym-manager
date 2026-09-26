package com.raulmartin.gym_manager.member.application.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.member.domain.model.Member;
import com.raulmartin.gym_manager.member.domain.port.in.FindMemberByIdUseCase;
import com.raulmartin.gym_manager.member.domain.port.out.MemberRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindMemberByIdService implements FindMemberByIdUseCase{

    private final MemberRepositoryPort memberRepositoryPort;

    @Override
    public Optional<Member> findById(UUID id) {
        return memberRepositoryPort.findById(id);

    }
    
}

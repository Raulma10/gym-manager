package com.raulmartin.gym_manager.member.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.member.domain.model.Member;
import com.raulmartin.gym_manager.member.domain.port.in.ChangeMemberStatusUseCase;
import com.raulmartin.gym_manager.member.domain.port.out.MemberRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ChangeMemberStatusService implements ChangeMemberStatusUseCase{

    private final MemberRepositoryPort memberRepositoryPort;

    @Override
    public Member changeMemberStatus(UUID id) {
        Member member = memberRepositoryPort.findById(id).orElseThrow(() -> new IllegalArgumentException("No se ha encontrado al socio"));
        Member updatedMember = member.changeStatus(member.getMemberStatus());
        return memberRepositoryPort.save(updatedMember);

    }
    
}

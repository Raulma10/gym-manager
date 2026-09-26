package com.raulmartin.gym_manager.member.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.member.domain.port.in.DeleteMemberUseCase;
import com.raulmartin.gym_manager.member.domain.port.out.MemberRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class DeleteMemberService implements DeleteMemberUseCase{
    
    private final MemberRepositoryPort memberRepositoryPort;
    
    @Override
    public void deleteMember(UUID id) {
        memberRepositoryPort.findById(id).orElseThrow(() -> new IllegalArgumentException("No se ha encontrado al socio"));
        memberRepositoryPort.deleteById(id);
    }
    
}

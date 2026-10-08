package com.raulmartin.gym_manager.member.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.member.domain.model.Member;
import com.raulmartin.gym_manager.member.domain.port.in.RemoveWorkoutFromMemberUseCase;
import com.raulmartin.gym_manager.member.domain.port.out.MemberRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class RemoveWorkoutFromMemberService implements RemoveWorkoutFromMemberUseCase{
    private final MemberRepositoryPort memberRepositoryPort;

    @Override
    public Member removeWorkout(UUID memberId) {
        Member member = memberRepositoryPort.findById(memberId).orElseThrow(() ->new IllegalArgumentException("No se ha encontrado el socio"));

        if (member.getWorkoutId() == null) {
            throw new IllegalArgumentException("El socio no tiene ninguna tabla asignada");
        }

        Member updatedMember = member.removeWorkout();

        return memberRepositoryPort.save(updatedMember);
    }

}

package com.raulmartin.gym_manager.member.application.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.fee.domain.model.Fee;
import com.raulmartin.gym_manager.member.domain.model.Member;
import com.raulmartin.gym_manager.member.domain.port.in.CreateMemberUseCase;
import com.raulmartin.gym_manager.member.domain.port.out.MemberRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateMemberService implements CreateMemberUseCase{

    private final MemberRepositoryPort memberRepositoryPort;

    @Override
    public Member create(String name, String lastName, String mail, String phone, LocalDate birthDate, UUID feeId) {
        Member member = Member.create(name, lastName, mail, phone, birthDate, feeId);
        return memberRepositoryPort.save(member);
    }
    
}

package com.raulmartin.gym_manager.member.application.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.fee.domain.model.Fee;
import com.raulmartin.gym_manager.member.domain.model.Member;
import com.raulmartin.gym_manager.member.domain.model.MemberStatus;
import com.raulmartin.gym_manager.member.domain.port.in.SearchMemberUseCase;
import com.raulmartin.gym_manager.member.domain.port.out.MemberRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchMemberService implements SearchMemberUseCase{
    
    private final MemberRepositoryPort memberRepositoryPort;
    
    @Override
    public Page<Member> searchMember(String name, String lastName, String mail, String phone, LocalDate birthDate,
            Fee fee, MemberStatus memberStatus, Pageable pageable) {
        return memberRepositoryPort.searchMember(name, lastName, mail, phone, birthDate, fee, memberStatus, pageable);
    }
    
}

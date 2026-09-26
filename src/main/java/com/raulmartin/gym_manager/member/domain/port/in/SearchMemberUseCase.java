package com.raulmartin.gym_manager.member.domain.port.in;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.raulmartin.gym_manager.fee.domain.model.Fee;
import com.raulmartin.gym_manager.member.domain.model.Member;
import com.raulmartin.gym_manager.member.domain.model.MemberStatus;

public interface SearchMemberUseCase {
    Page<Member> searchMember(String name, String lastName, String mail, String phone, LocalDate birthDate, Fee fee, MemberStatus memberStatus, Pageable pageable);
}

package com.raulmartin.gym_manager.member.domain.port.out;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.raulmartin.gym_manager.fee.domain.model.Fee;
import com.raulmartin.gym_manager.member.domain.model.Member;
import com.raulmartin.gym_manager.member.domain.model.MemberStatus;

public interface MemberRepositoryPort {
    Member save(Member member);
    Optional<Member> findById(UUID id);
    List<Member> findAll();
    Page<Member> searchMember(String name, String lastName, String mail, String phone, LocalDate birthDate, Fee fee, MemberStatus memberStatus, Pageable pageable);
    void deleteById(UUID id);
}

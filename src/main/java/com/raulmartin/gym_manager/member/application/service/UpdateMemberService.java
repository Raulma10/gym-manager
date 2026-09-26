package com.raulmartin.gym_manager.member.application.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.member.domain.model.Member;
import com.raulmartin.gym_manager.member.domain.port.in.UpdateMemberUseCase;
import com.raulmartin.gym_manager.member.domain.port.out.MemberRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UpdateMemberService implements UpdateMemberUseCase{

    private final MemberRepositoryPort memberRepositoryPort;

    @Override
    public Member update(UUID id, String name, String lastName, String mail, String phone, LocalDate birthDate) {
        Member member = memberRepositoryPort.findById(id).orElseThrow(() -> new IllegalArgumentException("No se ha encontrado al socio"));
        String newName = member.getName();
        String newLastName = member.getLastName();
        String newMail = member.getMail();
        String newPhone = member.getPhone();
        LocalDate newBirthDate = member.getBirthDate();

        if (name != null) {
            newName = name;
        }
        if (lastName != null) {
            newLastName = lastName;
        }
        if (mail != null) {
            newMail = mail;
        }
        if (phone != null) {
            newPhone = phone;
        }
        if (birthDate != null) {
            newBirthDate = birthDate;
        }

        Member updatedMember = member.update(newName, newLastName, newMail, newPhone, newBirthDate);

        return memberRepositoryPort.save(updatedMember);
    }
    
}

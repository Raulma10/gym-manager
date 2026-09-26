package com.raulmartin.gym_manager.member.domain.model;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor  
public class Member {
    
    private final UUID id;
    private final String name;
    private final String lastName;
    private final String mail;
    private final String phone;
    private final LocalDate birthDate;
    private final UUID feeId;
    private final MemberStatus memberStatus;

    public static Member create(String name, String lastName, String mail, String phone, LocalDate birthDate, UUID feeId){
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if(lastName == null || lastName.isBlank()){
            throw new IllegalArgumentException("El apellido es obligatorio");
        }
        if(mail == null || mail.isBlank()){
            throw new IllegalArgumentException("El mail es obligatorio");
        }
        if(birthDate == null){
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria");
        }
        if(phone == null || phone.isBlank()){
            throw new IllegalArgumentException("El número de teléfono es obligatorio");
        }

        if(feeId == null){
            throw new IllegalArgumentException("Debe asignar una cuota");
        }
        

        return new Member(UUID.randomUUID(), name, lastName, mail, phone, birthDate, feeId, MemberStatus.ACTIVE);
    }

    public Member update(String name, String lastName, String mail, String phone, LocalDate birthDate){
        Member updated = Member.create(name, lastName, mail, phone, birthDate, feeId);
        return new Member(this.id, updated.name, updated.lastName, updated.mail, updated.phone, updated.birthDate, this.feeId, this.memberStatus);
    }

    public Member changeStatus(MemberStatus memberStatus){
        
        MemberStatus newStatus = null;

        if (memberStatus.equals(MemberStatus.ACTIVE)){
            newStatus = MemberStatus.INACTIVE;
        }
        if (memberStatus.equals(MemberStatus.INACTIVE)){
            newStatus = MemberStatus.ACTIVE;
        }
        return new Member(this.id, this.name, this.lastName, this.mail, this.phone, this.birthDate, this.feeId, newStatus);
    }
}

package com.raulmartin.gym_manager.member.infrastructure.in.web.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateMemberRequest(

    @Size(min = 3, max = 40)
    String name,
    
    @Size(min = 3, max = 50)
    String lastName,
    
    @Email 
    String mail,
    
    @Pattern(regexp = "^\\+?[0-9]{9,15}$")
    String phone,
    
    @Past 
    LocalDate birthDate
) {
}

package com.raulmartin.gym_manager.member.infrastructure.in.web.dto;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateMemberRequest(
    @NotBlank 
    @NotNull 
    @Size(min = 3, max = 40)
    String name,
    
    @NotBlank 
    @NotNull 
    @Size(min = 3, max = 50)
    String lastName,
    
    @NotBlank 
    @NotNull 
    @Email 
    String mail,
    
    @NotBlank 
    @NotNull 
    @Pattern(regexp = "^\\+?[0-9]{9,15}$")
    String phone,
    
    @NotNull 
    @Past 
    LocalDate birthDate,

    @NotNull 
    UUID feeId
) {
}

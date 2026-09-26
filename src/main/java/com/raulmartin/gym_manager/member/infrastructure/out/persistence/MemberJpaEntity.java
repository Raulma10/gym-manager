package com.raulmartin.gym_manager.member.infrastructure.out.persistence;

import java.time.LocalDate;
import java.util.UUID;

import com.raulmartin.gym_manager.fee.infrastructure.out.persistence.FeeJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity 
@Table(name = "members")
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
public class MemberJpaEntity {
    
    @Id
    private UUID id;
 
    @NotNull
    @NotBlank
    @Size(min = 3, max = 40)
    private String name;
    
    @NotNull
    @NotBlank
    @Size(min = 3, max = 50)
    private String lastName;
 
    @NotNull
    @NotBlank
    @Email
    @Column(unique = true, nullable = false)
    private String mail;
 
    @NotNull
    @Pattern(regexp = "^\\+?[0-9]{9,15}$")
    private String phone;
 
    @Past
    @NotNull 
    @Column(nullable = false)
    private LocalDate birthDate;
 
    @ManyToOne(optional = false)
    @JoinColumn(name = "fee_id",nullable = false)
    private FeeJpaEntity fee;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberStatusJpa memberStatus; 


}

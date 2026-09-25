package com.raulmartin.gym_manager.fee.infrastructure.out.persistence;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeeJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
 
    @NotNull
    @NotBlank
    @Column(unique = true, nullable = false)
    private String name;
 
    @DecimalMin("0.0")
    private BigDecimal price;
 
    @Min(0)
    @Max(2)
    private int weeklySessions;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FeeStatusJpa feeStatus;
}

package com.raulmartin.gym_manager.member.infrastructure.in.web.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.raulmartin.gym_manager.fee.infrastructure.in.web.dto.FeeResponse;
import com.raulmartin.gym_manager.member.domain.model.MemberStatus;
import com.raulmartin.gym_manager.workout.infrastructure.in.web.dto.WorkoutResponse;

public record MemberResponse(
        UUID id,
        String name,
        String lastName,
        String mail,
        String phone,
        LocalDate birthDate,
        FeeResponse fee,
        MemberStatus memberStatus,
        WorkoutResponse workout
) {
    
}

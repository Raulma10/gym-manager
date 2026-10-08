package com.raulmartin.gym_manager.member.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.member.domain.model.Member;
import com.raulmartin.gym_manager.member.domain.port.in.AssignWorkoutToMemberUseCase;
import com.raulmartin.gym_manager.member.domain.port.out.MemberRepositoryPort;
import com.raulmartin.gym_manager.workout.domain.port.out.WorkoutRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AssignWorkoutToMemberService implements AssignWorkoutToMemberUseCase{
    
    private final MemberRepositoryPort memberRepositoryPort;
    private final WorkoutRepositoryPort workoutRepositoryPort;

    @Override
    public Member assignWorkout(UUID memberId, UUID workoutId) {
        Member member = memberRepositoryPort.findById(memberId).orElseThrow(() -> new IllegalArgumentException("No se ha encontrado el miembro"));
        workoutRepositoryPort.findById(workoutId).orElseThrow(() -> new IllegalArgumentException("No se ha encontrado la tabla de ejercicios"));

        Member updated = member.assignWorkout(workoutId);
        return memberRepositoryPort.save(updated);
    }
}

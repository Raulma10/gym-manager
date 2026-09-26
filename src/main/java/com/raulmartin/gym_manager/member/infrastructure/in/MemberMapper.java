package com.raulmartin.gym_manager.member.infrastructure.in;

import org.mapstruct.Mapper;

import com.raulmartin.gym_manager.member.domain.model.Member;
import com.raulmartin.gym_manager.member.infrastructure.in.web.dto.MemberResponse;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberResponse toResponse(Member member);
    
}

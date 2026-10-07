package com.raulmartin.gym_manager.member.infrastructure.out.persistence.mapper;

import org.mapstruct.Mapper;

import com.raulmartin.gym_manager.member.domain.model.MemberStatus;
import com.raulmartin.gym_manager.member.infrastructure.out.persistence.MemberStatusJpa;

@Mapper(componentModel = "spring")
public interface MemberStatusPersistenceMapper {
    
    MemberStatusJpa toEntityMemberStatusJpa(MemberStatus memberStatus);
    MemberStatus toDomainMemberStatus(MemberStatusJpa memberStatusJpa);
}

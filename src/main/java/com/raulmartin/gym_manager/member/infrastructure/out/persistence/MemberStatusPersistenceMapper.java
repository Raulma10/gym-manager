package com.raulmartin.gym_manager.member.infrastructure.out.persistence;

import org.mapstruct.Mapper;

import com.raulmartin.gym_manager.member.domain.model.MemberStatus;

@Mapper(componentModel = "spring")
public interface MemberStatusPersistenceMapper {
    
    MemberStatusJpa toEntityMemberStatusJpa(MemberStatus memberStatus);
    MemberStatus toDomainMemberStatus(MemberStatusJpa memberStatusJpa);
}

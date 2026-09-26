package com.raulmartin.gym_manager.member.infrastructure.out.persistence;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.raulmartin.gym_manager.member.domain.model.Member;

@Mapper(componentModel = "spring")
public interface MemberPersistenceMapper {

    @Mapping(target = "fee", ignore = true)
    MemberJpaEntity toEntity(Member member);

    @Mapping(target = "changeStatus", ignore = true)
    @Mapping(target = "feeId", source = "fee.id")
    Member toDomain(MemberJpaEntity entity);
}

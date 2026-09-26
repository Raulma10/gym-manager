package com.raulmartin.gym_manager.member.infrastructure.out.persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.raulmartin.gym_manager.fee.domain.model.Fee;
import com.raulmartin.gym_manager.fee.infrastructure.out.persistence.FeeJpaEntity;
import com.raulmartin.gym_manager.fee.infrastructure.out.persistence.FeeJpaRepository;
import com.raulmartin.gym_manager.fee.infrastructure.out.persistence.FeePersistenceMapper;
import com.raulmartin.gym_manager.member.domain.model.Member;
import com.raulmartin.gym_manager.member.domain.model.MemberStatus;
import com.raulmartin.gym_manager.member.domain.port.out.MemberRepositoryPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class MemberPersistenceAdapter implements MemberRepositoryPort{

    private final MemberJpaRepository memberJpaRepository;
    private final FeeJpaRepository feeJpaRepository;
    private final MemberPersistenceMapper memberPersistenceMapper;
    private final MemberStatusPersistenceMapper memberStatusPersistenceMapper;
    private final FeePersistenceMapper feePersistenceMapper;


    @Override
    public Member save(Member member) {
        MemberJpaEntity memberJpaEntity = memberPersistenceMapper.toEntity(member);

        FeeJpaEntity fee = feeJpaRepository.findById(member.getFeeId()).orElseThrow(() -> new IllegalArgumentException("No se ha encontrado la cuota"));

        memberJpaEntity.setFee(fee);
        MemberJpaEntity saved = memberJpaRepository.save(memberJpaEntity);
        return memberPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Member> findById(UUID id) {
        return memberJpaRepository
                .findById(id)
                .map(memberPersistenceMapper::toDomain);
    }

    @Override
    public List<Member> findAll() {
        return memberJpaRepository
                .findAll()
                .stream()
                .map(memberPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Page<Member> searchMember(String name, String lastName, String mail, String phone, LocalDate birthDate,
            Fee fee, MemberStatus memberStatus, Pageable pageable) {

        MemberStatusJpa memberStatusJpa = null;
        if(memberStatus != null){
            memberStatusJpa = memberStatusPersistenceMapper.toEntityMemberStatusJpa(memberStatus);
        }

        FeeJpaEntity feeJpaEntity = null;
        if (fee != null) {
            feeJpaEntity = feePersistenceMapper.toEntity(fee);
        }

        return memberJpaRepository.searchMember(
            name, 
            lastName, 
            mail, 
            phone, 
            birthDate, 
            feeJpaEntity, 
            memberStatusJpa, 
            pageable)
            .map(memberPersistenceMapper::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        memberJpaRepository.deleteById(id);
    }
    
}

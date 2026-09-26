package com.raulmartin.gym_manager.member.infrastructure.out.persistence;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.raulmartin.gym_manager.fee.infrastructure.out.persistence.FeeJpaEntity;

public interface MemberJpaRepository extends JpaRepository<MemberJpaEntity,UUID>{
    @Query("""
        SELECT m
        FROM MemberJpaEntity m
        WHERE (:name IS NULL OR LOWER(m.name) LIKE LOWER(CONCAT('%', :name, '%')))
        AND (:lastName IS NULL OR LOWER(m.lastName) LIKE LOWER(CONCAT('%', :lastName, '%')))
        AND (:mail IS NULL OR m.mail = :mail)
        AND (:phone IS NULL OR m.phone = :phone)
        AND (:birthDate IS NULL OR m.birthDate = :birthDate)
        AND (:fee IS NULL OR m.fee = :fee)
        AND (:memberStatus IS NULL OR m.memberStatus = :memberStatus)
        """)
    Page<MemberJpaEntity> searchMember(
            @Param("name") String name, 
            @Param("lastName") String lastName, 
            @Param("mail") String mail, 
            @Param("phone") String phone, 
            @Param("birthDate") LocalDate birthDate,
            @Param("fee") FeeJpaEntity fee,
            @Param("memberStatus") MemberStatusJpa memberStatus,
            Pageable pageable
    );
}

package com.raulmartin.gym_manager.fee.infrastructure.out.persistence;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FeeJpaRepository extends JpaRepository<FeeJpaEntity,UUID>{
    @Query("""
        SELECT f
        FROM FeeJpaEntity f
        WHERE (:name IS NULL OR LOWER(f.name) LIKE LOWER(CONCAT('%', :name, '%')))
        AND (:price IS NULL OR f.price = :price)
        AND (:weeklySessions IS NULL OR f.weeklySessions = :weeklySessions)
        AND (:status IS NULL OR f.feeStatus = :status)
        """)
    Page<FeeJpaEntity> searchFee(
            @Param("name") String name,
            @Param("price") BigDecimal price,
            @Param("weeklySessions") Integer weeklySessions,
            @Param("status") FeeStatusJpa status,
            Pageable pageable
    );
}

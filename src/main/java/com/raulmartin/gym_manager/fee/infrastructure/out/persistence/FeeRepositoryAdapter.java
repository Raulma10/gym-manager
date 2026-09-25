package com.raulmartin.gym_manager.fee.infrastructure.out.persistence;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.raulmartin.gym_manager.fee.domain.model.Fee;
import com.raulmartin.gym_manager.fee.domain.model.FeeStatus;
import com.raulmartin.gym_manager.fee.domain.port.out.FeeRepositoryPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class FeeRepositoryAdapter implements FeeRepositoryPort{

    private final FeeJpaRepository feeJpaRepository;
    private final FeePersistenceMapper feePersistenceMapper;
    private final FeeStatusPersistenceMapper feeStatusPersistenceMapper;
    
    @Override
    public Fee save(Fee fee) {
        FeeJpaEntity feeEntity = feePersistenceMapper.toEntity(fee);
        FeeJpaEntity saved = feeJpaRepository.save(feeEntity);
        return feePersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Fee> findById(UUID id) {
        return feeJpaRepository
                .findById(id)
                .map(feePersistenceMapper::toDomain);
    }

    @Override
    public List<Fee> findAll() {
        return feeJpaRepository
                .findAll()
                .stream()
                .map(feePersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        feeJpaRepository.deleteById(id);
    }

    @Override
    public Page<Fee> searchFee(String name, BigDecimal price, Integer weeklySessions, FeeStatus status, Pageable pageable) {
        FeeStatusJpa statusJpa = null;

        if (status != null) {
            statusJpa = feeStatusPersistenceMapper
                    .toEntityFeeStatusJpa(status);
        }
        
        return feeJpaRepository
            .searchFee(
                    name,
                    price,
                    weeklySessions,
                    statusJpa,
                    pageable
            )
            .map(feePersistenceMapper::toDomain);
    }
    
}

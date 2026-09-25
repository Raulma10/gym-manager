package com.raulmartin.gym_manager.fee.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class Fee {


    private final UUID id;
    private final String name;
    private final BigDecimal price;
    private final int weeklySessions;
    private final FeeStatus feeStatus;

    public static Fee create(String name, BigDecimal price, int weeklySessions){
        
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("El nombre de la cuota es obligatorio");
        }
        
        if(price == null || price.signum() < 0){
            throw new IllegalArgumentException("El precio no puede se negativo");
        }
        
        if(weeklySessions < 0 || weeklySessions >2){
            throw new IllegalArgumentException("Las sesiones semanales deben ser entre 0 y 2 (incluidos)");
        }

        return new Fee(UUID.randomUUID(), name, price, weeklySessions, FeeStatus.ACTIVE);
    }

    public Fee update(String newName, BigDecimal newPrice, int newWeeklySessions){
        Fee updated = Fee.create(newName, newPrice, newWeeklySessions);
        return new Fee(this.id, updated.getName(), updated.getPrice(), updated.getWeeklySessions(), this.feeStatus);
    }

    public Fee changeStatus(FeeStatus feeStatus){
        
        FeeStatus newStatus = null;

        if (feeStatus.equals(FeeStatus.ACTIVE)){
            newStatus = FeeStatus.INACTIVE;
        }
        if (feeStatus.equals(FeeStatus.INACTIVE)){
            newStatus = FeeStatus.ACTIVE;
        }
        return new Fee(this.id, this.name, this.price, this.weeklySessions, newStatus);
    }

}

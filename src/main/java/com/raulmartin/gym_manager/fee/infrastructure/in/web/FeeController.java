package com.raulmartin.gym_manager.fee.infrastructure.in.web;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raulmartin.gym_manager.fee.domain.model.Fee;
import com.raulmartin.gym_manager.fee.domain.port.in.CreateFeeUseCase;
import com.raulmartin.gym_manager.fee.domain.port.in.FindFeeByIdUseCase;
import com.raulmartin.gym_manager.fee.domain.port.in.ListFeesUseCase;
import com.raulmartin.gym_manager.fee.infrastructure.in.web.dto.CreateFeeRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/fees") 
@RequiredArgsConstructor 
public class FeeController {
    private final CreateFeeUseCase createFeeUseCase;
    private final ListFeesUseCase listFeesUseCase;
    private final FindFeeByIdUseCase findFeeByIdUseCase;

    @PostMapping
    public ResponseEntity<Fee> createFee(@Valid @RequestBody CreateFeeRequest createFeeRequest){
        Fee fee = createFeeUseCase.createFee(
            createFeeRequest.name(),
            createFeeRequest.price(),
            createFeeRequest.weeklySessions()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(fee);
    }

    @GetMapping
    public ResponseEntity<List<Fee>> listFees(){
        return ResponseEntity.ok(listFeesUseCase.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fee> findFeeById(@PathVariable UUID id){
        return findFeeByIdUseCase.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

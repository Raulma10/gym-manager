package com.raulmartin.gym_manager.fee.infrastructure.in.web;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raulmartin.gym_manager.fee.domain.model.Fee;
import com.raulmartin.gym_manager.fee.domain.model.FeeStatus;
import com.raulmartin.gym_manager.fee.domain.port.in.ChangeStatusUseCase;
import com.raulmartin.gym_manager.fee.domain.port.in.CreateFeeUseCase;
import com.raulmartin.gym_manager.fee.domain.port.in.DeleteFeeUseCase;
import com.raulmartin.gym_manager.fee.domain.port.in.FindFeeByIdUseCase;
import com.raulmartin.gym_manager.fee.domain.port.in.FindFeesUseCase;
import com.raulmartin.gym_manager.fee.domain.port.in.ListFeesUseCase;
import com.raulmartin.gym_manager.fee.domain.port.in.UpdateFeeUseCase;
import com.raulmartin.gym_manager.fee.infrastructure.in.web.dto.CreateFeeRequest;
import com.raulmartin.gym_manager.fee.infrastructure.in.web.dto.FeeResponse;
import com.raulmartin.gym_manager.fee.infrastructure.in.web.dto.UpdateFeeRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/fees") 
@RequiredArgsConstructor 
public class FeeController {

    private final FeeMapper feeMapper;

    private final CreateFeeUseCase createFeeUseCase;
    private final ListFeesUseCase listFeesUseCase;
    private final FindFeeByIdUseCase findFeeByIdUseCase;
    private final FindFeesUseCase findFeesUseCase;
    private final UpdateFeeUseCase updateFeeUseCase;
    private final ChangeStatusUseCase changeStatusUseCase;
    private final DeleteFeeUseCase deleteFeeUseCase;

    @PostMapping
    public ResponseEntity<FeeResponse> createFee(@Valid @RequestBody CreateFeeRequest createFeeRequest){
       
        Fee fee = createFeeUseCase.createFee(
            createFeeRequest.name(),
            createFeeRequest.price(),
            createFeeRequest.weeklySessions()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(feeMapper.toResponse(fee));
    }

    @GetMapping
    public ResponseEntity<List<FeeResponse>> listFees(){
        
        List<FeeResponse> fees = listFeesUseCase.listAll()
            .stream()
            .map(feeMapper::toResponse)
            .toList();
        
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(fees);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<FeeResponse>> searchFees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) BigDecimal price,
            @RequestParam(required = false) Integer weeklySessions,
            @RequestParam(required = false) FeeStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<FeeResponse> fees = findFeesUseCase
                .searchFee(
                        name,
                        price,
                        weeklySessions,
                        status,
                        pageable
                )
                .map(feeMapper::toResponse);

        return ResponseEntity.ok(fees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeeResponse> findFeeById(@PathVariable UUID id){
        return findFeeByIdUseCase.findById(id)
                .map(feeMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                                    .status(HttpStatus.NOT_FOUND)
                                    .build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FeeResponse> updateFee(@PathVariable UUID id, @Valid @RequestBody UpdateFeeRequest request) {
        Fee fee = updateFeeUseCase.updateFee(
                id,
                request.name(),
                request.price(),
                request.weeklySessions()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(feeMapper.toResponse(fee));
        
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<FeeResponse> updateFeeStatus(@PathVariable UUID id){
        Fee fee = changeStatusUseCase.changeFeeStatus(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(feeMapper.toResponse(fee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFee(@PathVariable UUID id) {
        deleteFeeUseCase.deleteFee(id);
        return ResponseEntity
            .status(HttpStatus.OK)
            .build();
    }
}

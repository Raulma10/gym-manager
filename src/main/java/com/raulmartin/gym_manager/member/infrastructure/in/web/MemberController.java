package com.raulmartin.gym_manager.member.infrastructure.in.web;

import java.time.LocalDate;
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
import com.raulmartin.gym_manager.fee.infrastructure.in.web.FeeMapper;
import com.raulmartin.gym_manager.member.domain.model.Member;
import com.raulmartin.gym_manager.member.domain.model.MemberStatus;
import com.raulmartin.gym_manager.member.domain.port.in.ChangeMemberStatusUseCase;
import com.raulmartin.gym_manager.member.domain.port.in.CreateMemberUseCase;
import com.raulmartin.gym_manager.member.domain.port.in.DeleteMemberUseCase;
import com.raulmartin.gym_manager.member.domain.port.in.FindMemberByIdUseCase;
import com.raulmartin.gym_manager.member.domain.port.in.ListMembersUseCase;
import com.raulmartin.gym_manager.member.domain.port.in.SearchMemberUseCase;
import com.raulmartin.gym_manager.member.domain.port.in.UpdateMemberUseCase;
import com.raulmartin.gym_manager.member.infrastructure.in.MemberMapper;
import com.raulmartin.gym_manager.member.infrastructure.in.web.dto.CreateMemberRequest;
import com.raulmartin.gym_manager.member.infrastructure.in.web.dto.MemberResponse;
import com.raulmartin.gym_manager.member.infrastructure.in.web.dto.UpdateMemberRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/members")
@RequiredArgsConstructor 
public class MemberController {
    
    private final MemberMapper memberMapper;
    private final CreateMemberUseCase createMemberUseCase;
    private final UpdateMemberUseCase updateMemberUseCase;
    private final ListMembersUseCase listMembersUseCase;
    private final SearchMemberUseCase searchMemberUseCase;
    private final FindMemberByIdUseCase findMemberByIdUseCase;
    private final ChangeMemberStatusUseCase changeMemberStatusUseCase;
    private final DeleteMemberUseCase deleteMemberUseCase;

    @PostMapping 
    public ResponseEntity<MemberResponse> createMember(@Valid @RequestBody CreateMemberRequest createMemberRequest){
        Member member = createMemberUseCase.create(
            createMemberRequest.name(), 
            createMemberRequest.lastName(),
            createMemberRequest.mail(),
            createMemberRequest.phone(),
            createMemberRequest.birthDate(),
            createMemberRequest.feeId()
        );

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(memberMapper.toResponse(member));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MemberResponse>> listMembers(){
        List<MemberResponse> members = listMembersUseCase.findAll()
            .stream()
            .map(memberMapper::toResponse)
            .toList();

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(members);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> findMemberById(@PathVariable UUID id){
        return findMemberByIdUseCase.findById(id)
            .map(memberMapper::toResponse)
            .map(ResponseEntity::ok)
            .orElseGet(()-> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .build());
    }

    @GetMapping 
    public ResponseEntity<Page<MemberResponse>> searchMembers(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String lastName,
        @RequestParam(required = false) String mail,
        @RequestParam(required = false) String phone,
        @RequestParam(required = false) LocalDate birthDate,
        @RequestParam(required = false) Fee fee,
        @RequestParam(required = false) MemberStatus memberStatus,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size);

        Page<MemberResponse> members = searchMemberUseCase
                .searchMember(
                    name, 
                    lastName, 
                    mail, 
                    phone, 
                    birthDate, 
                    fee, 
                    memberStatus, 
                    pageable
        )
        .map(memberMapper::toResponse);

        return ResponseEntity.ok(members);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MemberResponse> updateMember(@PathVariable UUID id, @Valid @RequestBody UpdateMemberRequest updateMemberRequest){
        Member member = updateMemberUseCase.update(
            id, 
            updateMemberRequest.name(),
            updateMemberRequest.lastName(),
            updateMemberRequest.mail(),
            updateMemberRequest.phone(),
            updateMemberRequest.birthDate());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberMapper.toResponse(member));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<MemberResponse> changeMemberStatus(@PathVariable UUID id){
        Member member = changeMemberStatusUseCase.changeMemberStatus(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberMapper.toResponse(member));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable UUID id){
        deleteMemberUseCase.deleteMember(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

}

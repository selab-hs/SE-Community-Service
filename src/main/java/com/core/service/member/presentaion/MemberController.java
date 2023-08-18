package com.core.service.member.presentaion;

import com.core.service.common.response.dto.ResponseDto;
import com.core.service.common.response.dto.ResponseMessage;
import com.core.service.member.application.MemberService;
import com.core.service.member.dto.request.CreateMemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> joinMember(@RequestBody @Valid CreateMemberRequest request) {
        var response = memberService.joinMember(request);
        return ResponseDto.toResponseEntity(ResponseMessage.CREATED, response);
    }

    @GetMapping
    public ResponseEntity<ResponseDto> searchAllMember() {
        var response = memberService.searchAllMember();

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, response);
    }
}

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

    /**
     * @param CreateMemberRequest : 회원가입 유저 정보 Dto
     * @todo 이후 토큰이 있는 상태라면 진행이 불가능하도록 추가 예정,
     * @todp 이후 @Valid가 아닌 vo 생성자로 유효성 검사 진행 예정
     * @return MemberResponse : 회원 정보 ResponseDto
     */
    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> joinMember(@RequestBody @Valid CreateMemberRequest request) {
        var response = memberService.joinMember(request);
        return ResponseDto.toResponseEntity(ResponseMessage.CREATE_SUCCESS_MEMBER, response);
    }

    /**
     * @todo 일반 유저권한으로는 모든 멤버 조회 불가하도록 추가 예정
     */
    @GetMapping
    public ResponseEntity<ResponseDto> searchAllMember() {
        var response = memberService.searchAllMember();

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS_SEARCH_ALL_MEMBER, response);
    }
}

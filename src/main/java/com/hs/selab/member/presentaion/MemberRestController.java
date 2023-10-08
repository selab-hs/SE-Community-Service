package com.hs.selab.member.presentaion;

import com.hs.selab.auth.domain.UserDetail;
import com.hs.selab.auth.infrastructure.annotation.AuthMember;
import com.hs.selab.common.response.dto.ResponseDto;
import com.hs.selab.common.response.dto.ResponseMessage;
import com.hs.selab.member.application.MemberService;
import com.hs.selab.member.dto.request.CreateMemberRequest;
import com.hs.selab.member.dto.request.UpdateMemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberRestController {
    private final MemberService memberService;

    /**
     * @param request : 회원가입 유저 정보 Dto
     * @todo 이후 토큰이 있는 상태라면 진행이 불가능하도록 추가 예정,
     * @return MemberResponse : 회원 정보 ResponseDto
     */
    @PostMapping("/signup")
    public ResponseEntity<?> joinMember(@RequestBody @Valid CreateMemberRequest request) {
        var response = memberService.joinMember(request);

        return ResponseDto.toResponseEntity(ResponseMessage.CREATE_SUCCESS_MEMBER, response);
    }

    @PatchMapping("/change")
    public ResponseEntity<?> updateMember(@AuthMember UserDetail detail
            , @RequestBody @Valid UpdateMemberRequest request) {
        var response = memberService.updateMember(detail, request);

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS_UPDATE_MEMBER, response);
    }

    /**
     * @todo 일반 유저권한으로는 모든 멤버 조회 불가하도록 추가 예정
     */
    @GetMapping
    public ResponseEntity<?> searchAllMember() {
        var response = memberService.searchAllMember();

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS_SEARCH_ALL_MEMBER, response);
    }
}

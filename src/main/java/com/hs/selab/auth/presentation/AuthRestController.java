package com.hs.selab.auth.presentation;

import com.hs.selab.auth.application.AuthService;
import com.hs.selab.auth.dto.request.JoinRequest;
import com.hs.selab.common.response.dto.ResponseDto;
import com.hs.selab.common.response.dto.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthRestController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JoinRequest request) {
        var userDetail = authService.userLogin(request);

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS_LOAD_MEMBER_INFORMATION, userDetail);
    }


}

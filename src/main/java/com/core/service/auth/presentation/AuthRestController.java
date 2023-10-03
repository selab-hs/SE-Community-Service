package com.core.service.auth.presentation;

import com.core.service.auth.application.AuthService;
import com.core.service.auth.dto.request.JoinRequest;
import com.core.service.common.response.dto.ResponseDto;
import com.core.service.common.response.dto.ResponseMessage;
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

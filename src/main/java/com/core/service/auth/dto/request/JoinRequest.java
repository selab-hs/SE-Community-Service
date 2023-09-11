package com.core.service.auth.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JoinRequest {
    private String email;
    private String password;
}

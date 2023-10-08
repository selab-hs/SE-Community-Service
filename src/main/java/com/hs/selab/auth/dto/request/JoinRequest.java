package com.hs.selab.auth.dto.request;


import lombok.Getter;

@Getter
public class JoinRequest {
    private String email;
    private String password;
}

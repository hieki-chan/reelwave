package org.reelwave.identity.dto;

import lombok.Getter;

@Getter
public class RegisterRequest {
    String username;
    String email;
    String password;
}

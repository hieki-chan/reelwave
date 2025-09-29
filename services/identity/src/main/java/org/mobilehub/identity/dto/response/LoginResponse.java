package org.mobilehub.identity.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {
    String token;
    boolean isAuthenticated;
}

package com.kzadha.model.json;


import com.kzadha.security.UserPrincipal;
import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String userName;
    private Long tempId;
    private String accessToken;
    private String role;
    private static final String tokenType = "Bearer";

    public JwtAuthenticationResponse(String accessToken, UserPrincipal userPrincipal) {
        this.accessToken = accessToken;
        this.userName = userPrincipal.getUsername();
        this.role = userPrincipal.getAuthorities().stream().findAny().get().getAuthority().toString();
        this.tempId = userPrincipal.getId();
    }

}

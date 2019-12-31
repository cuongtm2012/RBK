package com.vn.rbk.model;

import lombok.Data;

@Data
public class UserDTO {
    private String accessToken;
    private String displayName;
    private String email;
    private String userId;
}

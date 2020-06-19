package com.cg.smart_house.dto.host;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HostDtoResponse {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;

    public HostDtoResponse(Long id, String username, String password, String email, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
}

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
}

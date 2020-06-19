package com.cg.smart_house.dto.address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressDtoResponse {
    private Long id;
    private String name;
    private String province;

    public AddressDtoResponse(Long id, String name, String province) {
        this.id = id;
        this.name = name;
        this.province = province;
    }
}

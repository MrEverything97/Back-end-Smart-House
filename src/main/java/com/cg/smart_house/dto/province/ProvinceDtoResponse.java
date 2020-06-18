package com.cg.smart_house.dto.province;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProvinceDtoResponse {
    private Long id;
    private String name;

    public ProvinceDtoResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

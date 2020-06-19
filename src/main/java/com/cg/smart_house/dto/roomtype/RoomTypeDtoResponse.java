package com.cg.smart_house.dto.roomtype;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoomTypeDtoResponse {
    private Long id;
    private Integer type;

    public RoomTypeDtoResponse(Long id, Integer type) {
        this.id = id;
        this.type = type;
    }
}

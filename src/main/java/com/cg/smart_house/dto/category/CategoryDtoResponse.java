package com.cg.smart_house.dto.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDtoResponse {
    private Long id;
    private String name;

    public CategoryDtoResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

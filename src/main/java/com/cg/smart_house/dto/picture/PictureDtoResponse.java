package com.cg.smart_house.dto.picture;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PictureDtoResponse {
    private Long id;
    private String imageUrl;

    public PictureDtoResponse(Long id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }
}

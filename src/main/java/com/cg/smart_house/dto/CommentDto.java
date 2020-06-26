package com.cg.smart_house.dto;

import lombok.Data;

@Data
public class CommentDto {
    private String comment;
    private String username;
    private Long rate;
}

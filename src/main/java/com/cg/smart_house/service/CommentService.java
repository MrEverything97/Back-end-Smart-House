package com.cg.smart_house.service;


import com.cg.smart_house.model.Comment;
;

public interface CommentService {
    ServiceResult findAllCommentById (Long Id);
    ServiceResult saveComment (Comment comment);
}

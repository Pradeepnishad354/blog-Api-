package com.blog.service;

import java.util.List;

import com.blog.payloads.CommentDto;





public interface CommentService {
	
	
	CommentDto createComment(CommentDto commentDto,Integer postId);
	CommentDto updateComment(CommentDto CommentDto,Integer id);
	
	List<CommentDto> getAllComment();
	void deleteComment(Integer commentId);

}

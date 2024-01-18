package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.CommentDto;
import com.blog.service.CommentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/")
public class CommentController {
	
	@Autowired
	private CommentService  commentService;
	
	
	
	@PostMapping("/comments/{postId}/post")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable("postId") Integer postId){
		
		
		CommentDto createComment = this.commentService.createComment(commentDto, postId);
		
		
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
		
		
	}
	
@GetMapping("/comments/")
	public ResponseEntity<List<CommentDto>> getAllComment(){
		
		
		List<CommentDto> allComment = this.commentService.getAllComment();
		
		
		return new ResponseEntity<List<CommentDto>>(allComment,HttpStatus.OK);
		
		
	}
	
	
	
	
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") Integer commentId){
		
		
		 this.commentService.deleteComment( commentId);
		
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted successfully !!",true) ,HttpStatus.OK);
		
		
	}

}

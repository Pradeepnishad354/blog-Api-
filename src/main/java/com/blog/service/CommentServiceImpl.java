package com.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payloads.CommentDto;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;

@Service
public class CommentServiceImpl  implements CommentService{

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto,Integer postId) {
		
		
		Post post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postId", postId));
		
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		

		comment.setPost(post);
		
		Comment comment2 = this.commentRepository.save(comment);
	
		return this.modelMapper.map(comment2, CommentDto.class);
	}

	
	
	
	@Override
	public CommentDto updateComment(CommentDto commentDto, Integer id) {
		
		
		return null;
	}

	@Override
	public List<CommentDto> getAllComment() {
		
		List<Comment> allComment = this.commentRepository.findAll();	
		
		
		List<CommentDto> collect = allComment.stream().map((comment)->this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
		
		return collect;
	}

	@Override
	public void deleteComment(Integer commentId) {
		
	Comment comment = this.commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment","commentID",commentId));
		
	this.commentRepository.delete(comment);
	}
	
	

}

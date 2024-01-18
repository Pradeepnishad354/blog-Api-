package com.blog.service;

import java.util.List;

import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;

public interface  PostService {
	
	//create
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto,Integer postId);
	
	
	void deletePost(Integer postId);
	
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	PostDto getPostById(Integer postId);
	
	
	// get All Post By category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//get all POSTbY USER
	
   List<PostDto> getPostsByUser(Integer userId);
   
   
   
   //search post
   List<PostDto> searchPost(String keywords);
   
   
   
   
   




}

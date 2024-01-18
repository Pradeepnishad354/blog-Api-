package com.blog.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.repository.CategoryRepository;
import com.blog.repository.PostRepository;
import com.blog.repository.UserRepository;



@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);

		post.setImageName("default.png");

		post.setAddedDate(new Date());

		post.setUser(user);
		post.setCategory(category);

		Post newPost = this.postRepository.save(post);

		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "postid", postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		//post.setAddedDate(postDto.getAddedDate());
		Post post2 = this.postRepository.save(post);
		PostDto postDto2 = this.modelMapper.map(post2, PostDto.class);
		
		return postDto2;
	}

	@Override
	public void deletePost(Integer postId) {
	
		Post post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId));
           
		this.postRepository.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		
		
		//sorting 
		
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			
		      sort=	Sort.by(sortBy).ascending();
			
		}else {
			
			sort=Sort.by(sortBy).descending();
			
			
		}
		
		
		
		
		Pageable pageable=PageRequest.of(pageNumber, pageSize,sort);

		Page<Post> pagePost = postRepository.findAll(pageable);
		
		List<Post> postAll = pagePost.getContent();

		List<PostDto> postDto = postAll.stream().map(((post) -> this.modelMapper.map(post, PostDto.class)))
				.collect(Collectors.toList());
		
		
		PostResponse postResponse=new PostResponse();
		
		postResponse.setContent(postDto);
		
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
 Post orElseThrow = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postId", postId));

 return this.modelMapper.map(orElseThrow,PostDto.class);

		
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {

		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category Id", categoryId));
		List<Post> posts = postRepository.findByCategory(category);
		List<PostDto> postDto = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDto;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {

		User users = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "user Id", userId));

		List<Post> posts = postRepository.findByUser(users);
		List<PostDto> postDto = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDto;
	}

	@Override
	public List<PostDto> searchPost(String keywords) {
	
	  List<Post> postList = postRepository.searchByTitle("%"+keywords+"%");
	
	   List<PostDto> list = postList.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return list;
	}

}

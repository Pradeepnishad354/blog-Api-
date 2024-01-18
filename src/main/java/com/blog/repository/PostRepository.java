package com.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;

public interface PostRepository extends JpaRepository<Post,Integer> {
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category catgeory);
	
	@Query("select p from Post p where p.title like :key")
	List<Post> searchByTitle(@Param("key")String keyword);

	
	
	
	
}

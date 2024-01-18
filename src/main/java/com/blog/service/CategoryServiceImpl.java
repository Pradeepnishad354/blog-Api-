package com.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Category;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payloads.CategoryDto;
import com.blog.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto,Category.class);
		
		Category save = this.categoryRepository.save(category);
		
		return this.modelMapper.map(save,CategoryDto.class);
		
		
		
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

		Category cat=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category ","category id", categoryId));
		
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCat = this.categoryRepository.save(cat);
		
		
		return this.modelMapper.map(updatedCat,CategoryDto.class);
	}
	

	@Override
	public void deleteCategory(Integer categoryId) {
		
	Category cat=	this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
		
	   this.categoryRepository.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
	Category cate=	this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
		
		
		
		return this.modelMapper.map(cate, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		
		List<Category> allCategory = this.categoryRepository.findAll();
		
		List<CategoryDto> collect = allCategory.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		
		return collect;
	}

}

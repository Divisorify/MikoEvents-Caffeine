package com.example.MikoEvents.category;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MikoEvents.exception.ResourceNotFoundException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper;

	public List<CategoryDto> getAllCategories() {
		return categoryMapper.mapToDto(categoryRepository.findAll());
	}

	public void addCategory(CategoryDto CategoryDto) {
		var category = Category.builder()
				.name(CategoryDto.getName())
				.categoryType(CategoryDto.getCategoryType())
				.build();

		categoryRepository.save(category);
	}

	public CategoryDto getCategoryById(Long id) {
		var category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found"));
		return categoryMapper.mapToDto(category);
	}


	@Transactional
	public void deleteCategoryById(Long id) {
		categoryRepository.deleteById(id);
	}

	@Transactional
	public CategoryDto editCategoryById(Long id, CategoryDto CategoryDto) {
		var Category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found"));

		Category.setName(CategoryDto.getName());
		Category.setCategoryType(CategoryDto.getCategoryType());

		categoryRepository.save(Category);

		return CategoryDto;
	}
}

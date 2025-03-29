package com.example.MikoEventsCaffeine.category;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	CategoryDto mapToDto(Category category);

	List<CategoryDto> mapToDto(List<Category> categoryList);
}

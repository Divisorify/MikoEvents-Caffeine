package com.example.MikoEvents.category;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	CategoryDto mapToDto(Category category);
}

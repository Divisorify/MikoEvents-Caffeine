package com.example.MikoEvents.category;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;

	@ApiOperation(value = "Dodaj nowy Category", notes = "Oczekuje: Body/raw/JSON")
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public void addCategory(@RequestBody @Valid CategoryDto categoryDto) {
		categoryService.addCategory(categoryDto);
	}

	@ApiOperation(value = "Wyświetlanie wszystkich Categoryów", notes = "Wyświetlanie wszystkich Categoryów")
	@GetMapping
	public List<CategoryDto> getAllCategories() {
		return categoryService.getAllCategories();
	}

	@ApiOperation(value = "Wyświetlanie Category po CategoryId")
	@GetMapping("/{id}")
	public CategoryDto getCategoryById(@PathVariable Long id) {
		return categoryService.getCategoryById(id);
	}

	@ApiOperation(value = "Usuwanie Category po CategoryId")
	@DeleteMapping
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteCategoryById(@PathVariable Long id) {
		categoryService.deleteCategoryById(id);
	}

	@ApiOperation(value = "Edytowanie Category po CategoryId")
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public CategoryDto editCategoryById(@PathVariable Long id, @RequestBody @Valid CategoryDto categoryDto) {
		return categoryService.editCategoryById(id, categoryDto);
	}
}

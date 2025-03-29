package com.example.MikoEventsCaffeine.category;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

	@NotNull
	private String name;

	@NotNull
	private CategoryType categoryType;
}

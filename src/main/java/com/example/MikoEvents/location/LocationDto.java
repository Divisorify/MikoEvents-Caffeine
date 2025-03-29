package com.example.MikoEvents.location;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {

	@NotNull
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String city;

	@NotNull
	private String address;

	private String zipCode;
}

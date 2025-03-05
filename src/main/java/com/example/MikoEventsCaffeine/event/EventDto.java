package com.example.MikoEventsCaffeine.event;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

	@NotNull
	private String name;

	@NotNull
	private String description;

	@NotNull
	private LocalDate date;

	@NotNull
	private LocalDateTime dateFrom;

	@NotNull
	private LocalDateTime dateTo;

	@NotNull
	private Long locationId;

	@NotNull
	private Long organizerId;

	@NotNull
	private Long categoryId;
}

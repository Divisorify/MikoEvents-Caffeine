package com.example.MikoEvents.registration;

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
public class RegistrationDto {

	@NotNull
	private Long userId;

	@NotNull
	private Long eventId;

	private LocalDateTime registrationDate;
}

package com.example.MikoEvents.registration;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {

	private Long userId;
	private Long eventId;
	private LocalDateTime registrationDate;
}

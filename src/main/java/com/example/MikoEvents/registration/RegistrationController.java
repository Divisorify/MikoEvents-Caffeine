package com.example.MikoEvents.registration;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

	private final RegistrationService registrationService;
}

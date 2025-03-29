package com.example.MikoEventsCaffeine.auth;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService service;

	@ApiOperation(value = "Rejestracja użytkownika")
	@PostMapping("/register")
	public AuthenticationResponse register(@RequestBody RegisterRequest request) {
		return service.register(request);
	}

	@ApiOperation(value = "Logowanie")
	@PostMapping("/authenticate")
	public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request) {
		return service.authenticate(request);
	}

	@ApiOperation(value = "Odświeżanie tokenu")
	@PostMapping("/refresh-token")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		service.refreshToken(request, response);
	}


}

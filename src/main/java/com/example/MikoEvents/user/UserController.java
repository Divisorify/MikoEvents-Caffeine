package com.example.MikoEvents.user;

import java.security.Principal;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@ApiOperation(value = "Zmiana hasła")
	@PatchMapping
	public void changePassword(@RequestBody ChangePasswordRequest request,
							   Principal connectedUser) {
		userService.changePassword(request, connectedUser);
	}
}

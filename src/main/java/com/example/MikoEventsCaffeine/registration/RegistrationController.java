package com.example.MikoEventsCaffeine.registration;

import java.util.List;

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
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

	private final RegistrationService registrationService;

	@ApiOperation(value = "Dodaj nowego registration", notes = "Oczekuje: Body/raw/JSON")
	@PostMapping
	public void addRegistration(@RequestBody @Valid RegistrationDto registrationDto) {
		registrationService.addRegistration(registrationDto);
	}

	@ApiOperation(value = "Wyświetlanie wszystkich registration", notes = "Wyświetlanie wszystkich registration")
	@GetMapping
	public List<RegistrationDto> getAllRegistrations() {
		return registrationService.getAllRegistrations();
	}

	@ApiOperation(value = "Wyświetlanie registration po registrationId")
	@GetMapping("/{id}")
	public RegistrationDto getRegistrationById(@PathVariable Long id) {
		return registrationService.getRegistrationById(id);
	}

	@ApiOperation(value = "Usuwanie registration po registrationId")
	@DeleteMapping
	public void deleteRegistrationById(@PathVariable Long id) {
		registrationService.deleteRegistrationById(id);
	}

	@ApiOperation(value = "Edytowanie registration po registrationId")
	@PutMapping("/{id}")
	public RegistrationDto editRegistrationById(@PathVariable Long id, @RequestBody @Valid RegistrationDto registrationDto) {
		return registrationService.editRegistrationById(id, registrationDto);
	}
}

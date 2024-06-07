package com.example.MikoEvents.organizer;

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
@RequestMapping("/organizer")
@RequiredArgsConstructor
public class OrganizerController {

	private final OrganizerService organizerService;

	@ApiOperation(value = "Dodaj nowego organizatora", notes = "Oczekuje: Body/raw/JSON")
	@PostMapping
	public void addOrganizer(@RequestBody @Valid OrganizerDto organizerDto) {
		organizerService.addOrganizer(organizerDto);
	}

	@ApiOperation(value = "Wyświetlanie wszystkich organizatorów", notes = "Wyświetlanie wszystkich organizatorów")
	@GetMapping
	public List<OrganizerDto> getAllOrganizers() {
		return organizerService.getAllOrganizers();
	}

	@ApiOperation(value = "Wyświetlanie Organizer po OrganizerId")
	@GetMapping("/{id}")
	public OrganizerDto getOrganizerById(@PathVariable Long id) {
		return organizerService.getOrganizerById(id);
	}

	@ApiOperation(value = "Usuwanie Organizer po OrganizerId")
	@DeleteMapping
	public void deleteOrganizerById(@PathVariable Long id) {
		organizerService.deleteOrganizerById(id);
	}

	@ApiOperation(value = "Edytowanie Organizer po OrganizerId")
	@PutMapping("/{id}")
	public OrganizerDto editOrganizerById(@PathVariable Long id, @RequestBody @Valid OrganizerDto organizerDto) {
		return organizerService.editOrganizerById(id, organizerDto);
	}
}

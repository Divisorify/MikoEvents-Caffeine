package com.example.MikoEventsCaffeine.event;

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
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

	private final EventService eventService;

	@ApiOperation(value = "Dodaj nowy event", notes = "Oczekuje: Body/raw/JSON")
	@PostMapping
	public void addEvent(@RequestBody @Valid EventDto eventDto) {
		eventService.addEvent(eventDto);
	}

	@ApiOperation(value = "Wyświetlanie wszystkich eventów", notes = "Wyświetlanie wszystkich eventów")
	@GetMapping
	public List<EventDto> getAllEvents() {
		return eventService.getAllEvents();
	}

	@ApiOperation(value = "Wyświetlanie event po eventId")
	@GetMapping("/{id}")
	public EventDto getEventById(@PathVariable Long id) {
		return eventService.getEventById(id);
	}

	@ApiOperation(value = "Usuwanie event po eventId")
	@DeleteMapping
	public void deleteEventById(@PathVariable Long id) {
		eventService.deleteEventById(id);
	}

	@ApiOperation(value = "Edytowanie event po eventId")
	@PutMapping("/{id}")
	public EventDto editEventById(@PathVariable Long id, @RequestBody @Valid EventDto eventDto) {
		return eventService.editEventById(id, eventDto);
	}
}

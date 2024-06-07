package com.example.MikoEvents.Location;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MikoEvents.location.LocationDto;
import com.example.MikoEvents.location.LocationService;

import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
public class LocationController {

	private final LocationService locationService;

	@ApiOperation(value = "Dodaj nowy Location", notes = "Oczekuje: Body/raw/JSON")
	@PostMapping
	public void addLocation(@RequestBody @Valid LocationDto locationDto) {
		locationService.addLocation(locationDto);
	}

	@ApiOperation(value = "Wyświetlanie wszystkich Locationów", notes = "Wyświetlanie wszystkich Locationów")
	@GetMapping
	public List<LocationDto> getAllLocations() {
		return locationService.getAllLocations();
	}

	@ApiOperation(value = "Wyświetlanie Location po LocationId")
	@GetMapping("/{id}")
	public LocationDto getLocationById(@PathVariable Long id) {
		return locationService.getLocationById(id);
	}

	@ApiOperation(value = "Usuwanie Location po LocationId")
	@DeleteMapping
	public void deleteLocationById(@PathVariable Long id) {
		locationService.deleteLocationById(id);
	}

	@ApiOperation(value = "Edytowanie Location po LocationId")
	@PutMapping("/{id}")
	public LocationDto editLocationById(@PathVariable Long id, @RequestBody @Valid LocationDto locationDto) {
		return locationService.editLocationById(id, locationDto);
	}
}

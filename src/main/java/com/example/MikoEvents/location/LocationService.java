package com.example.MikoEvents.location;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MikoEvents.exception.ResourceNotFoundException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationService {

	private final LocationRepository locationRepository;
	private final LocationMapper locationMapper;

	public List<LocationDto> getAllLocations() {
		return locationMapper.mapToDto(locationRepository.findAll());
	}

	public void addLocation(LocationDto locationDto) {
		var location = Location.builder()
				.name(locationDto.getName())
				.city(locationDto.getCity())
				.address(locationDto.getAddress())
				.zipCode(locationDto.getZipCode())
				.build();

		locationRepository.save(location);
	}

	public LocationDto getLocationById(Long id) {
		var location = locationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Location not found"));
		return locationMapper.mapToDto(location);
	}


	@Transactional
	public void deleteLocationById(Long id) {
		locationRepository.deleteById(id);
	}

	@Transactional
	public LocationDto editLocationById(Long id, LocationDto locationDto) {
		var location = locationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Location not found"));

		location.setName(locationDto.getName());
		location.setCity(locationDto.getCity());
		location.setAddress(locationDto.getAddress());
		location.setZipCode(locationDto.getZipCode());

		locationRepository.save(location);

		return locationDto;
	}
}

package com.example.MikoEvents.location;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.example.MikoEvents.exception.ResourceNotFoundException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"location"})
@Slf4j
public class LocationService {

	private final LocationRepository locationRepository;
	private final LocationMapper locationMapper;
	private final String FILE_NAME = "src/main/resources/fileWithLocations.txt";

	public List<LocationDto> getAllLocations() {
		log.info("Cache miss!");

		try {
			// Create ObjectMapper instance
			ObjectMapper objectMapper = new ObjectMapper();

			// Read JSON from the .txt file and map to a list of SomeDTO
			List<LocationDto> dtoList = objectMapper.readValue(
					new File("src/main/resources/fileWithLocations.txt"),
					new TypeReference<List<LocationDto>>() {
					}
			);

			return dtoList;
		} catch (IOException e) {
			System.err.println("An error occurred while reading the file: " + e.getMessage());
		}

		return List.of();
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

	public void addLocations(Set<LocationDto> locationDtos) {
		var locationsToSave = new ArrayList<Location>();
		for (int i = 0; i < 1000; i++) {
			int finalI = i;
			locationDtos.forEach(v -> locationsToSave.add(buildLocation(v, finalI * 1000)));
		}
		try {
			// Create a file object
			File file = new File(FILE_NAME);
			file.createNewFile();

			// Use BufferedWriter to write data to the file
			FileWriter writer = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);

			ObjectMapper objectMapper = new ObjectMapper();
			String jsonString = objectMapper.writeValueAsString(locationsToSave);
			bufferedWriter.write(jsonString);
			bufferedWriter.close();

			System.out.println("File created and data written successfully.");
		} catch (IOException e) {
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
		}

	}

	private Location buildLocation(LocationDto locationDto, int idAddition) {
		var zipCode = Optional.ofNullable(locationDto.getZipCode())
				.orElse("35-050");

		return Location.builder()
				.id(locationDto.getId() + idAddition)
				.name(locationDto.getName())
				.city(locationDto.getCity())
				.address(locationDto.getAddress())
				.zipCode(zipCode)
				.build();
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

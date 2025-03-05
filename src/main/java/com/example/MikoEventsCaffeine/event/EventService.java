package com.example.MikoEventsCaffeine.event;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MikoEventsCaffeine.category.CategoryRepository;
import com.example.MikoEventsCaffeine.exception.ResourceNotFoundException;
import com.example.MikoEventsCaffeine.location.LocationRepository;
import com.example.MikoEventsCaffeine.organizer.OrganizerRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {

	private final EventRepository eventRepository;
	private final LocationRepository locationRepository;
	private final OrganizerRepository organizerRepository;
	private final CategoryRepository categoryRepository;
	private final EventMapper eventMapper;

	public List<EventDto> getAllEvents() {
		return eventMapper.mapToDto(eventRepository.findAll());
	}

	public void addEvent(EventDto eventDto) {
		var location = locationRepository.findById(eventDto.getLocationId())
				.orElseThrow(() -> new ResourceNotFoundException("Location not found"));
		var organizer = organizerRepository.findById(eventDto.getOrganizerId())
				.orElseThrow(() -> new ResourceNotFoundException("Organizer not found"));
		var category = categoryRepository.findById(eventDto.getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException("Category not found"));
		var event = Event.builder()
				.name(eventDto.getName())
				.description(eventDto.getDescription())
				.date(eventDto.getDate())
				.dateFrom(eventDto.getDateFrom())
				.dateTo(eventDto.getDateTo())
				.location(location)
				.organizer(organizer)
				.category(category)
				.build();

		eventRepository.save(event);
	}

	public EventDto getEventById(Long id) {
		var event = eventRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Event not found"));
		return eventMapper.mapToDto(event);
	}


	@Transactional
	public void deleteEventById(Long id) {
		eventRepository.deleteById(id);
	}

	@Transactional
	public EventDto editEventById(Long id, EventDto eventDto) {
		var event = eventRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Event not found"));

		var location = locationRepository.findById(eventDto.getLocationId())
				.orElseThrow(() -> new ResourceNotFoundException("Location not found"));
		var organizer = organizerRepository.findById(eventDto.getOrganizerId())
				.orElseThrow(() -> new ResourceNotFoundException("Organizer not found"));
		var category = categoryRepository.findById(eventDto.getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException("Category not found"));

		event.setName(eventDto.getName());
		event.setDescription(eventDto.getDescription());
		event.setDate(eventDto.getDate());
		event.setDateFrom(eventDto.getDateFrom());
		event.setDateTo(eventDto.getDateTo());
		event.setLocation(location);
		event.setOrganizer(organizer);
		event.setCategory(category);

		eventRepository.save(event);

		return eventDto;
	}
}

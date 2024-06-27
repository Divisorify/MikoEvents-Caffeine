package com.example.MikoEvents.event;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {

	@Mapping(target = "organizerId", source = "organizer.id")
	@Mapping(target = "categoryId", source = "category.id")
	@Mapping(target = "locationId", source = "location.id")
	EventDto mapToDto(Event event);

	@Mapping(target = "organizerId", source = "organizer.id")
	@Mapping(target = "categoryId", source = "category.id")
	@Mapping(target = "locationId", source = "location.id")
	List<EventDto> mapToDto(List<Event> eventList);
}

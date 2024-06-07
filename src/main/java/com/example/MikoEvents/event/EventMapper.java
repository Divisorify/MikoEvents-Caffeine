package com.example.MikoEvents.event;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

	EventDto mapToDto(Event event);

	List<EventDto> mapToDto(List<Event> eventList);
}

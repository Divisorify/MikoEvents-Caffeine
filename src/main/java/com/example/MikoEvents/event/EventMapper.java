package com.example.MikoEvents.event;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

	EventDto mapToDto(Event event);
}

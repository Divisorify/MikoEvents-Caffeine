package com.example.MikoEvents.organizer;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrganizerMapper {

	OrganizerDto mapToDto(Organizer organizer);
}

package com.example.MikoEvents.organizer;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrganizerMapper {

	OrganizerDto mapToDto(Organizer organizer);

	List<OrganizerDto> mapToDto(List<Organizer> organizerList);
}

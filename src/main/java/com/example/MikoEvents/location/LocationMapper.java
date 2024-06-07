package com.example.MikoEvents.location;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {

	LocationDto mapToDto(Location location);
}

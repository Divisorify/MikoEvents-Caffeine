package com.example.MikoEventsCaffeine.location;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {

	LocationDto mapToDto(Location location);

	List<LocationDto> mapToDto(List<Location> locationList);
}

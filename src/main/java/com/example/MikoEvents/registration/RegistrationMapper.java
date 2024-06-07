package com.example.MikoEvents.registration;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {

	RegistrationDto mapToDto(Registration registration);
}

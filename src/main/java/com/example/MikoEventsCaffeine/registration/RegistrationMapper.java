package com.example.MikoEventsCaffeine.registration;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {

	RegistrationDto mapToDto(Registration registration);

	List<RegistrationDto> mapToDto(List<Registration> registrationList);
}

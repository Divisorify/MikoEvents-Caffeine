package com.example.MikoEventsCaffeine.registration;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MikoEventsCaffeine.exception.ResourceNotFoundException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrationService {

	private final RegistrationRepository registrationRepository;
	private final RegistrationMapper registrationMapper;

	public List<RegistrationDto> getAllRegistrations() {
		return registrationMapper.mapToDto(registrationRepository.findAll());
	}

	public void addRegistration(RegistrationDto registrationDto) {
		var registration = Registration.builder()
				.userId(registrationDto.getUserId())
				.eventId(registrationDto.getEventId())
				.registrationDate(LocalDateTime.now())
				.build();

		registrationRepository.save(registration);
	}

	public RegistrationDto getRegistrationById(Long id) {
		var registration = registrationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("registration not found"));
		return registrationMapper.mapToDto(registration);
	}


	@Transactional
	public void deleteRegistrationById(Long id) {
		registrationRepository.deleteById(id);
	}

	@Transactional
	public RegistrationDto editRegistrationById(Long id, RegistrationDto registrationDto) {
		var registration = registrationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("registration not found"));

		registration.setUserId(registrationDto.getUserId());
		registration.setEventId(registrationDto.getEventId());

		registrationRepository.save(registration);

		return registrationDto;
	}
}

package com.example.MikoEventsCaffeine.organizer;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MikoEventsCaffeine.exception.ResourceNotFoundException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrganizerService {

	private final OrganizerRepository organizerRepository;
	private final OrganizerMapper organizerMapper;

	public List<OrganizerDto> getAllOrganizers() {
		return organizerMapper.mapToDto(organizerRepository.findAll());
	}

	public void addOrganizer(OrganizerDto organizerDto) {
		var organizer = Organizer.builder()
				.name(organizerDto.getName())
				.contactInfo(organizerDto.getContactInfo())
				.build();

		organizerRepository.save(organizer);
	}

	public OrganizerDto getOrganizerById(Long id) {
		var organizer = organizerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Organizer not found"));
		return organizerMapper.mapToDto(organizer);
	}


	@Transactional
	public void deleteOrganizerById(Long id) {
		organizerRepository.deleteById(id);
	}

	@Transactional
	public OrganizerDto editOrganizerById(Long id, OrganizerDto organizerDto) {
		var organizer = organizerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Organizer not found"));

		organizer.setName(organizerDto.getName());
		organizer.setContactInfo(organizerDto.getContactInfo());

		organizerRepository.save(organizer);

		return organizerDto;
	}
}

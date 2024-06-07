package com.example.MikoEvents.organizer;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrganizerService {

	private final OrganizerRepository organizerRepository;
}

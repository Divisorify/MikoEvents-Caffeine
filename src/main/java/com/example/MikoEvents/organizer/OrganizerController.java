package com.example.MikoEvents.organizer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/organizer")
@RequiredArgsConstructor
public class OrganizerController {

	private final OrganizerService organizerService;
}

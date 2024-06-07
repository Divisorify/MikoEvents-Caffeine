package com.example.MikoEvents.event;

import java.time.LocalDate;

import com.example.MikoEvents.category.Category;
import com.example.MikoEvents.location.Location;
import com.example.MikoEvents.organizer.Organizer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

	private String eventName;

	private String description;

	private LocalDate date;

	private LocalDate dateFrom;

	private LocalDate dateTo;

	private Location location;

	private Organizer organizer;

	private Category category;
}

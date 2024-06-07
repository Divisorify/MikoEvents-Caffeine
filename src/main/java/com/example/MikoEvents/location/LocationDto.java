package com.example.MikoEvents.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {

	private String locationName;

	private String city;

	private String address;

	private String zipCode;
}

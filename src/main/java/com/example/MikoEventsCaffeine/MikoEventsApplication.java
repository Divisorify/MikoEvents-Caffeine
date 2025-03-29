package com.example.MikoEventsCaffeine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@SpringBootApplication
@EnableJpaRepositories
public class MikoEventsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MikoEventsApplication.class, args);
	}

}


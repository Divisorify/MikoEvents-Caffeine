package com.example.MikoEvents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = CacheAutoConfiguration.class)
@EnableJpaRepositories
public class MikoEventsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MikoEventsApplication.class, args);
	}
}


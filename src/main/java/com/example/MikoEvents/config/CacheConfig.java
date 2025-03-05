package com.example.MikoEvents.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CacheConfig {
	private static final Logger logger = LoggerFactory.getLogger(CacheConfig.class);

	@Bean
	@Primary
	public CacheManager cacheManager() {
		logger.info("Using NoOpCacheManager - caching is disabled");
		return new NoOpCacheManager();
	}
}

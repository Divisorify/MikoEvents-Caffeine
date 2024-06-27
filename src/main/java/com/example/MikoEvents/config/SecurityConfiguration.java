package com.example.MikoEvents.config;

import static com.example.MikoEvents.user.Permission.ADMIN_CREATE;
import static com.example.MikoEvents.user.Permission.ADMIN_DELETE;
import static com.example.MikoEvents.user.Permission.ADMIN_READ;
import static com.example.MikoEvents.user.Permission.ADMIN_UPDATE;
import static com.example.MikoEvents.user.Permission.USER_CREATE;
import static com.example.MikoEvents.user.Permission.USER_DELETE;
import static com.example.MikoEvents.user.Permission.USER_READ;
import static com.example.MikoEvents.user.Permission.USER_UPDATE;
import static com.example.MikoEvents.user.Role.ADMIN;
import static com.example.MikoEvents.user.Role.USER;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

	private static final String[] WHITE_LIST_URL = {"/auth/**",
			"/v2-docs",
			"/v3-docs",
			"/v3-docs/**",
			"/v3/api-docs/**",
			"/swagger-resources",
			"/swagger-resources/**",
			"/configuration/ui",
			"/configuration/security",
			"/swagger-ui/**",
			"/webjars/**",
			"/swagger-ui.html"};
	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;
	private final LogoutHandler logoutHandler;


	private SecurityScheme createAPIKeyScheme() {
		return new SecurityScheme().type(SecurityScheme.Type.HTTP)
				.bearerFormat("JWT")
				.scheme("bearer");
	}

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI().addSecurityItem(new SecurityRequirement().
				addList("Bearer Authentication"));
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//Disable CSRF and enable CORS
		http.cors();

		http
				.csrf(AbstractHttpConfigurer::disable)
				.cors(httpSecurityCorsConfigurer -> corsConfigurationSource())
				.authorizeHttpRequests(req ->
						req.requestMatchers(WHITE_LIST_URL)
								.permitAll()
								.requestMatchers("/user/**").hasAnyRole(ADMIN.name(), USER.name())
								.requestMatchers(GET, "/user/**").hasAnyAuthority(ADMIN_READ.name(), USER_READ.name())
								.requestMatchers(POST, "/user/**").hasAnyAuthority(ADMIN_CREATE.name(), USER_CREATE.name())
								.requestMatchers(PUT, "/user/**").hasAnyAuthority(ADMIN_UPDATE.name(), USER_UPDATE.name())
								.requestMatchers(DELETE, "/user/**").hasAnyAuthority(ADMIN_DELETE.name(), USER_DELETE.name())
								.anyRequest()
								.authenticated()
				)
				.sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
				.logout(logout ->
						logout.logoutUrl("/auth/logout")
								.addLogoutHandler(logoutHandler)
								.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
				);

		return http.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOriginPattern(CorsConfiguration.ALL);
		configuration.setAllowedMethods(List.of(CorsConfiguration.ALL));
		configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}

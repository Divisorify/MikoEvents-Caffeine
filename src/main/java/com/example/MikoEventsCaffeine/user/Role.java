package com.example.MikoEventsCaffeine.user;

import static com.example.MikoEventsCaffeine.user.Permission.ADMIN_CREATE;
import static com.example.MikoEventsCaffeine.user.Permission.ADMIN_DELETE;
import static com.example.MikoEventsCaffeine.user.Permission.ADMIN_READ;
import static com.example.MikoEventsCaffeine.user.Permission.ADMIN_UPDATE;
import static com.example.MikoEventsCaffeine.user.Permission.USER_CREATE;
import static com.example.MikoEventsCaffeine.user.Permission.USER_DELETE;
import static com.example.MikoEventsCaffeine.user.Permission.USER_READ;
import static com.example.MikoEventsCaffeine.user.Permission.USER_UPDATE;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {

	ADMIN(
			Set.of(
					ADMIN_READ,
					ADMIN_UPDATE,
					ADMIN_DELETE,
					ADMIN_CREATE,
					USER_READ,
					USER_UPDATE,
					USER_DELETE,
					USER_CREATE
			)
	),
	USER(
			Set.of(
					USER_READ,
					USER_UPDATE,
					USER_DELETE,
					USER_CREATE
			)
	);

	@Getter
	private final Set<Permission> permissions;

	public List<SimpleGrantedAuthority> getAuthorities() {
		var authorities = getPermissions()
				.stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
				.collect(Collectors.toList());
		authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return authorities;
	}
}

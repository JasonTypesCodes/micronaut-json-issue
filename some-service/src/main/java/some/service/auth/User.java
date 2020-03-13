package some.service.auth;

import io.micronaut.security.authentication.Authentication;

public class User {
	private String name;

	public User() {}

	public User(Authentication authentication) {
		this.name = authentication.getName();
	}

	public String getName() {
		return this.name;
	}
}

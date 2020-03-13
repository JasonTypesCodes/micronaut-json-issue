package some.service.auth;

import java.util.Optional;

import javax.inject.Singleton;

import io.micronaut.http.context.ServerRequestContext;
import io.micronaut.security.authentication.Authentication;

@Singleton
public class AuthService {

	public Optional<User> getCurrentUser() {
		return ServerRequestContext.currentRequest()
			.flatMap(request -> request.getUserPrincipal(Authentication.class))
			.map(User::new);
	}
}

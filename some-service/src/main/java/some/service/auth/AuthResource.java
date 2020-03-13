package some.service.auth;

import java.util.Optional;

import javax.inject.Inject;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Controller("/auth")
// @Secured(SecurityRule.IS_AUTHENTICATED)
public class AuthResource {

	@Inject
	AuthService authService;

	@Get
	public HttpResponse<User> whoAmI() {
		Optional<User> maybeUser = authService.getCurrentUser();
		if (maybeUser.isPresent()) {
			return HttpResponse.<User>ok(maybeUser.get());
		}

		return HttpResponse.notFound();
	}

}

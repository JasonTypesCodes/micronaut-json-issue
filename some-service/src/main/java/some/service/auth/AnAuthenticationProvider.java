package some.service.auth;

import java.util.ArrayList;

import javax.inject.Singleton;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetails;
import io.reactivex.Flowable;

@Singleton
public class AnAuthenticationProvider implements AuthenticationProvider {

	private final Logger log = LoggerFactory.getLogger(AnAuthenticationProvider.class);

	@Override
	public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
		log.info("Dumbly authenticating " + authenticationRequest.getIdentity());
		if (authenticationRequest.getIdentity().equals("admin") && authenticationRequest.getSecret().equals("admin")) {
				log.info("Success!");
				return Flowable.just(new UserDetails("admin", new ArrayList<>()));
		}
		log.info("Fail!");
		return Flowable.just(new AuthenticationFailed());
	}

}

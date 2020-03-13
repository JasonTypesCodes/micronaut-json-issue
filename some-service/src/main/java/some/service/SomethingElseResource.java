package some.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.inject.Inject;
import javax.validation.Valid;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Controller("/something-else")
// @Secured(SecurityRule.IS_ANONYMOUS)
public class SomethingElseResource {

	@Inject
	SomethingElseRepository somethingElseRepository;

	@Post
	public HttpResponse<SomethingElse> createSomethingElse(@Valid @Body SomethingElse somethingElse) throws URISyntaxException {
		if (somethingElse.getId() == null) {
			somethingElse = somethingElseRepository.save(somethingElse);
			return HttpResponse.<SomethingElse>created(new URI("/something-else/" + somethingElse.getId())).body(somethingElse);
		}

		return HttpResponse.badRequest();
	}

	@Get("/{id}")
	public HttpResponse<SomethingElse> getSomethingElse(Long id) {
		Optional<SomethingElse> result = somethingElseRepository.findById(id);
		if (result.isPresent()) {
			return HttpResponse.<SomethingElse>ok(result.get());
		}

		return HttpResponse.notFound();
	}
}

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

@Controller("/something")
public class SomethingResource {

	@Inject
	SomethingRepository somethingRepository;

	@Post
	public HttpResponse<Something> createSomething(@Valid @Body Something something) throws URISyntaxException {
		if (something.getId() == null) {
			something = somethingRepository.save(something);
			return HttpResponse.<Something>created(new URI("/something/" + something.getId())).body(something);
		}

		return HttpResponse.badRequest();
	}

	@Get("/{id}")
	public HttpResponse<Something> getSomething(Long id) {
		Optional<Something> result = somethingRepository.findById(id);
		if (result.isPresent()) {
			return HttpResponse.<Something>ok(result.get());
		}

		return HttpResponse.notFound();
	}
}

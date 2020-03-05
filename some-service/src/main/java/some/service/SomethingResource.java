package some.service;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

@Controller("/something")
public class SomethingResource {
	@Post
	public HttpResponse<Something> createSomething(@Valid @Body Something something) throws URISyntaxException {
		something.setId(1L);
		return HttpResponse.<Something>created(new URI("/something/" + something.getId())).body(something);
	}
}

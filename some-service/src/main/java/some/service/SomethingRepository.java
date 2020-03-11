package some.service;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface SomethingRepository extends CrudRepository<Something, Long> {}

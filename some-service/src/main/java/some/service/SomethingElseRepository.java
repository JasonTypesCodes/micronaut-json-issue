package some.service;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface SomethingElseRepository extends JpaRepository<SomethingElse, Long> {}

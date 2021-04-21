package repository.database;

import model.WebDomain;

import java.util.Set;

public interface WebDomainDatabaseRepository extends DatabaseRepository<WebDomain, Long> {
    // see: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#appendix.query.method.subject
    Set<WebDomain> findByNameContaining(String name);
    Set<WebDomain> findAllByOrderByPriceAsc();
}

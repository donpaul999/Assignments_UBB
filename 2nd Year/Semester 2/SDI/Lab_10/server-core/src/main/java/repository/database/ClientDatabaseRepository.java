package repository.database;

import model.Client;

import java.util.Set;

public interface ClientDatabaseRepository extends DatabaseRepository<Client, Long> {
    // see: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#appendix.query.method.subject
    Set<Client> findByNameContaining(String name);
    Set<Client> findAllByOrderByNameAsc();
}

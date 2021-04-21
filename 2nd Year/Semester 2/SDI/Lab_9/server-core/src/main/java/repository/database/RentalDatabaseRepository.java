package repository.database;

import model.Rental;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface RentalDatabaseRepository extends DatabaseRepository<Rental, Long> {
    Set<Rental> findRentalsByClientId(Long id);
    Set<Rental> findRentalsByDomainId(Long id);

    @Query("select r from Rental r where r.clientId in" +
            " (select c.id from Client c where c.name like concat(?1, '%'))")
    Set<Rental> findRentalsByClientName(String name);

    @Query("select r from Rental r where r.domainId in" +
            " (select d.id from WebDomain d where d.name like concat(?1, '%'))")
    Set<Rental> findRentalsByDomainName(String name);
}

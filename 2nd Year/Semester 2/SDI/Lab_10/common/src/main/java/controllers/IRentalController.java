package controllers;

import dto.ClientsDTO;
import dto.RentalDTO;
import dto.RentalsDTO;
import dto.WebDomainsDTO;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface IRentalController {
    /**
     * Returns all the rentals.
     *
     * @return the set of rentals
     */
    RentalsDTO getRentals();

    /**
     * Returns the rental with a given id.
     *
     * @param id the id of the rental
     * @return the rental
     */
    RentalDTO getRental(Long id);

    /**
     * Add rental to the rental repository
     *
     * @param rental
     * @return
     */
    ResponseEntity<?> addRental(RentalDTO rental);

    /**
     * Delete a rental from the rental repository.
     *
     * @param rentalId the rental's id to delete.
     * @return
     */
    ResponseEntity<?> deleteRental(Long rentalId);

    /**
     * Updates an existent rental.
     *
     * @param rental
     * @return
     */
    ResponseEntity<?> updateRental(RentalDTO rental);

    RentalsDTO findRentalsByClientName(String name);

    RentalsDTO findRentalsByDomainName(String name);

    /**
     * Returns clients with most rentals
     * @return the set of clients with most rentals
     */
    ClientsDTO getMostRentingClients();

    /**
     * Returns the most rented domains.
     * @return the set of the most rented domains.
     */
    WebDomainsDTO getMostRentedDomains();

    /**
     * Returns the years with the most rentals.
     * @return the set of years.
     */
    Set<String> getMostRentedYears();

    /**
     * Returns the most rented TLD
     * @return the set of most rented TLD
     */
    Set<String> getMostCommonTld();
}

package services;

import exceptions.ControllerException;
import model.Client;
import model.Rental;
import model.WebDomain;
import model.validators.ValidatorException;

import java.util.Set;

public interface IRentalService {

    /**
     * Returns all the rentals.
     *
     * @return the set of rentals
     */
    Set<Rental> getRentals();

    /**
     * Returns the rental with a given id.
     *
     * @param id the id of the rental
     * @return the rental
     */
    Rental getRental(Long id);

    /**
     * Add new rental to the rental repository.
     *
     * @param rental new rental
     * @throws ControllerException if:
     * <p> the rental's id already exists, or </p>
     * <p> the client or domain doesn't exist, or </p>
     * <p> the current date is not after the last rental of this domain, or </p>
     * <p> the start date of rental is not after the last rental of this domain. </p>
     */
    void addRental(Rental rental);


    /**
     * Delete a rental from the rental repository.
     *
     * @param rentalId the rental's id to delete.
     */
    void deleteRental(Long rentalId);

    /**
     * Updates an existent rental.
     *
     * @param rental
     * @throws ValidatorException
     */
    void updateRental(Rental rental) throws ValidatorException;

    /**
     * Returns all rentals for a given client id.
     * @param id the client's id
     * @return the set of rentals a the matching client id.
     */
    Set<Rental> filterRentalsByClientId(Long id);

    /**
     * Returns all rentals for a given web domain id.
     * @param id the web domain's id.
     * @return the set of rentals of the given web domain.
     */
    Set<Rental> filterRentalsByDomainId(Long id);

    /**
     * Returns all rentals for a given client name.
     * @param name the client's name
     * @return the set of rentals a the matching client name.
     */
    Set<Rental> filterRentalsByClientName(String name);

    /**
     * Returns all rentals for a given domain name.
     * @param name the domain's name
     * @return the set of rentals a the matching domain name.
     */
    Set<Rental> filterRentalsByDomainName(String name);

    /**
     * Returns clients with most rentals
     * @return the set of clients with most rentals
     */
    Set<Client> getMostRentingClients();

    /**
     * Returns the most rented domains.
     * @return the set of the most rented domains.
     */
    Set<WebDomain> getMostRentedDomains();

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

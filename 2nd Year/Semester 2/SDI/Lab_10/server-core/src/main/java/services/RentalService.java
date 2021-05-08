package services;

import exceptions.ControllerException;
import model.Client;
import model.Rental;
import model.WebDomain;
import model.validators.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.database.RentalDatabaseRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class RentalService implements IRentalService {
    @Autowired
    private RentalDatabaseRepository rentals;

    @Autowired
    private IClientService clientService;

    @Autowired
    private IWebDomainService webDomainService;

    public static final Logger log = LoggerFactory.getLogger(IRentalService.class);

    /**
     * Returns all the rentals.
     *
     * @return the set of rentals
     */
    public Set<Rental> getRentals() {
        log.trace("getRentals --- method entered");

        Iterable<Rental> rentals = this.rentals.findAll();
        Set<Rental> result = StreamSupport.stream(rentals.spliterator(), false).collect(Collectors.toSet());

        log.trace("getRentals: result={}", result);
        return result;

    }

    /**
     * Returns the rental with a given id.
     *
     * @param id the id of the rental
     * @return the rental
     */
    public Rental getRental(Long id) {
        log.trace("getRental --- method entered");

        Rental result = this.rentals.findById(id)
                .orElseThrow(() -> new ControllerException(String.format("Rental id not found: %d", id)));

        log.trace("getRentals: result={}", result);
        return result;
    }

    private String getRentalEndDate(Rental rental) {
        log.trace("getRentalEndDate --- method entered");

        var rentalStartDateString = rental.getStartDate();
        var rentalStartDateSplit = rentalStartDateString.split("-");

        String result = rentalStartDateSplit[0] + "-" +
                rentalStartDateSplit[1] + "-" +
                (Integer.parseInt(rentalStartDateSplit[2]) + rental.getDuration());
        log.trace("getRentalEndDate: result={}", result);
        return result;
    }

    private boolean domainCanBeRented(Rental oldRental, Rental newRental) {
        log.trace("domainCanBeRented --- method entered");

        Date oldRentalEndDate;
        try {
            oldRentalEndDate = new SimpleDateFormat("dd-MM-yyyy").parse(this.getRentalEndDate(oldRental));
        } catch (ParseException e) {
            throw new ControllerException(e.getMessage());
        }

        var newRentalStartDateString = newRental.getStartDate();
        Date newRentalStartDate;
        try {
            newRentalStartDate = new SimpleDateFormat("dd-MM-yyyy").parse(newRentalStartDateString);
        } catch (ParseException e) {
            throw new ControllerException(e.getMessage());
        }

        boolean result = newRentalStartDate.after(oldRentalEndDate);
        log.trace("domainCanBeRented: result={}", result);
        return result;
    }

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
    public void addRental(Rental rental) {
        log.trace("addRental - method entered: rental={}", rental);
        log.trace("addRental - method entered: clientService={}", clientService);

        this.clientService.getClient(rental.getClientId());
        this.webDomainService.getWebDomain(rental.getDomainId());

        var rentalsWithCurrentDomainId = this.filterRentalsByDomainId(rental.getDomainId());
        rentalsWithCurrentDomainId.stream()
                .filter(rental1 -> !this.domainCanBeRented(rental1, rental))
                .findAny()
                .ifPresent(rental1 -> {
                    log.error(String.format("WebDomain id is already rented: %d", rental.getDomainId()));
                    throw new ControllerException(String.format("WebDomain id is already rented: %d", rental.getDomainId()));
                });

        this.rentals.findById(rental.getId())
                .ifPresent(x -> {
                    log.error(String.format("Rental id already exists: %d", rental.getId()));
                    throw new ControllerException(String.format("Rental id already exists: %d", rental.getId()));
                });

        this.rentals.save(rental);

        String result = "Succes";

        log.trace("addRental: result={}", result);
    }


    /**
     * Delete a rental from the rental repository.
     *
     * @param rentalId the rental's id to delete.
     */
    public void deleteRental(Long rentalId) {
        log.trace("deleteRental --- method entered");

        this.rentals.findById(rentalId)
                .orElseThrow(() -> new ControllerException(String.format("Rental id not found: %d", rentalId)));

        this.rentals.deleteById(rentalId);

        String result = "Succes";
        log.trace("deleteRental: result={}", result);
    }

    /**
     * Updates an existent rental.
     *
     * @param rental
     * @throws ValidatorException
     */
    public void updateRental(Rental rental) throws ValidatorException {
        log.trace("updateRental --- method entered");

        this.rentals.findById(rental.getId())
                .orElseThrow(() -> new ControllerException(String.format("(Update) Rental id not found: %d", rental.getId())));

        this.rentals.save(rental);

        String result = "Succes";
        log.trace("updateRental: result={}", result);
    }

    /**
     * Returns all rentals for a given client id.
     * @param id the client's id
     * @return the set of rentals a the matching client id.
     */
    public Set<Rental> filterRentalsByClientId(Long id) {
        log.trace("filterRentalsByClientId --- method entered");

        Set<Rental> result = StreamSupport.stream(this.rentals.findAll().spliterator(), false)
                .filter(rental -> rental.getClientId().equals(id))
                .collect(Collectors.toSet());

        log.trace("filterRentalsByClientId: result={}", result);
        return result;
    }

    /**
     * Returns all rentals for a given web domain id.
     * @param id the web domain's id.
     * @return the set of rentals of the given web domain.
     */
    public Set<Rental> filterRentalsByDomainId(Long id) {
        log.trace("filterRentalsByDomainId --- method entered");

        Set<Rental> result = StreamSupport.stream(this.rentals.findAll().spliterator(), false)
                .filter(rental -> rental.getDomainId().equals(id))
                .collect(Collectors.toSet());

        log.trace("filterRentalsByDomainId: result={}", result);
        return result;
    }

    /**
     * Returns all rentals for a given client name.
     * @param name the client's name
     * @return the set of rentals a the matching client name.
     */
    public Set<Rental> filterRentalsByClientName(String name) {
        log.trace("filterRentalsByClientName --- method entered");
        Set<Rental> result = this.rentals.findRentalsByClientName(name);
        log.trace("filterRentalsByClientName: result={}", result);
        return result;
    }

    /**
     * Returns all rentals for a given domain name.
     * @param name the domain's name
     * @return the set of rentals a the matching domain name.
     */
    public Set<Rental> filterRentalsByDomainName(String name) {
        log.trace("filterRentalsByDomainName --- method entered");

        Set<Rental> result = this.rentals.findRentalsByDomainName(name).stream()
                .map(i -> StreamSupport.stream(this.rentals.findAll().spliterator(), false)
                        .filter(rental -> rental.getDomainId().equals(i.getId()))
                        .collect(Collectors.toSet())
                )
                .reduce(new HashSet<>(), (rentals1, rentals2) -> {
                    rentals1.addAll(rentals2);
                    return rentals1;
                });

        log.trace("filterRentalsByDomainName: result={}", result);
        return result;
    }

    /**
     * Returns clients with most rentals
     * @return the set of clients with most rentals
     */
    public Set<Client> getMostRentingClients() {
        log.trace("getMostRentingClients --- method entered");

        Iterable<Rental> rentals = this.rentals.findAll();
        Map<Client, Integer> rentingClientsOccurrences = StreamSupport.stream(rentals.spliterator(), false)
                .collect(Collectors.toMap(rental -> {
                    try {
                        return this.clientService.getClient(rental.getClientId());
                    } catch (Exception e) {
                        throw new ControllerException(e.getMessage());
                    }
                }, client -> 1, Integer::sum));

        int mostRentals = rentingClientsOccurrences.values().stream()
                .mapToInt(occurrences -> occurrences)
                .max()
                .orElse(0);

        Set<Client> result = rentingClientsOccurrences.entrySet().stream()
                .filter(clientOccurrences -> clientOccurrences.getValue() == mostRentals)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        log.trace("filterRentalsByDomainName: result={}", result);
        return result;
    }

    /**
     * Returns the most rented domains.
     * @return the set of the most rented domains.
     */
    public Set<WebDomain> getMostRentedDomains() {
        log.trace("getMostRentedDomains --- method entered");

        Iterable<Rental> rentals = this.rentals.findAll();
        Map<WebDomain, Integer> rentedDomainsOccurrences = StreamSupport.stream(rentals.spliterator(), false)
                .collect(Collectors.toMap(rental -> {
                    try {
                        return this.webDomainService.getWebDomain(rental.getDomainId());
                    } catch (Exception e) {
                        throw new ControllerException(e.getMessage());
                    }
                }, domain -> 1, Integer::sum));

        int maxOccurrences = rentedDomainsOccurrences.values().stream()
                .mapToInt(occurrences -> occurrences)
                .max()
                .orElse(0);

        Set<WebDomain> result = rentedDomainsOccurrences.entrySet().stream()
                .filter(domainOccurrence -> domainOccurrence.getValue() == maxOccurrences)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        log.trace("filterRentalsByDomainName: result={}", result);
        return result;
    }

    /**
     * Returns the years with the most rentals.
     * @return the set of years.
     */
    public Set<String> getMostRentedYears() {
        log.trace("getMostRentedYears --- method entered");

        Iterable<Rental> rentals = this.rentals.findAll();
        Map<String, Integer> rentedYearsOccurences = StreamSupport.stream(rentals.spliterator(), false)
                .collect(Collectors.toMap(rental -> rental.getStartDate().split("-")[2], rental -> 1, Integer::sum));

        int maxOccurrences = rentedYearsOccurences.values().stream()
                .mapToInt(occurrences -> occurrences)
                .max()
                .orElse(0);

        Set<String> result = rentedYearsOccurences.entrySet().stream()
                .filter(occurences -> occurences.getValue() == maxOccurrences)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        log.trace("filterRentalsByDomainName: result={}", result);
        return result;
    }

    /**
     * Returns the most rented TLD
     * @return the set of most rented TLD
     */
    public Set<String> getMostCommonTld() {
        log.trace("getMostCommonTld --- method entered");

        Iterable<Rental> rentals = this.rentals.findAll();
        Map<String, Integer> rentedTldOccurrences = StreamSupport.stream(rentals.spliterator(), false)
                .collect(Collectors.toMap(rental -> {
                            try {
                                String[] domainSplit = this.webDomainService.getWebDomain(rental.getDomainId())
                                        .getName()
                                        .split("\\.");
                                return domainSplit[domainSplit.length - 1];
                            } catch (Exception e) {
                                throw new ControllerException(e.getMessage());
                            }
                        }
                        ,domain -> 1, Integer::sum));

        int maxOccurrences = rentedTldOccurrences.values().stream()
                .mapToInt(occurrences -> occurrences)
                .max()
                .orElse(0);

        Set<String> result = rentedTldOccurrences.entrySet().stream()
                .filter(tldOccurrence -> tldOccurrence.getValue() == maxOccurrences)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        log.trace("filterRentalsByDomainName: result={}", result);
        return result;
    }
}

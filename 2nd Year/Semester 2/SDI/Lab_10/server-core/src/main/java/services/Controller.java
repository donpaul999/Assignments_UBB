package services;

import exceptions.ControllerException;
import model.ApplicationContext;
import model.Client;
import model.Rental;
import model.WebDomain;
import model.validators.ValidatorException;
import repository.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Controller {
    private Repository<Long, Client> clientRepository;
    private Repository<Long, WebDomain> domainRepository;
    private Repository<Long, Rental> rentalRepository;

    public Controller(Repository<Long, Client> clientRepository, Repository<Long, WebDomain> domainRepository, Repository<Long, Rental> rentalRepository) {
        this.clientRepository = clientRepository;
        this.domainRepository = domainRepository;
        this.rentalRepository = rentalRepository;
    }

    public Controller() {
    }

    @SuppressWarnings("unchecked")
    public void init(ApplicationContext context) {
        this.clientRepository = (Repository<Long, Client>) context.getShared("repository.client").orElse(null);
        this.domainRepository = (Repository<Long, WebDomain>) context.getShared("repository.webdomain").orElse(null);
        this.rentalRepository = (Repository<Long, Rental>) context.getShared("repository.rental").orElse(null);
    }

    /**
     * Returns all the clients.
     *
     * @return the set of clients
     */
    public Set<Client> getClients() {
        Iterable<Client> clients = this.clientRepository.findAll();
        return StreamSupport.stream(clients.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     * Returns all the domains.
     *
     * @return the set of domains
     */
    public Set<WebDomain> getDomains() {
        Iterable<WebDomain> domains = this.domainRepository.findAll();
        return StreamSupport.stream(domains.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     * Returns all the rentals.
     *
     * @return the set of rentals
     */
    public Set<Rental> getRentals() {
        Iterable<Rental> rentals = this.rentalRepository.findAll();
        return StreamSupport.stream(rentals.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     * Returns the client with a given id.
     *
     * @param id the id of the client
     * @return the client
     */
    public Client getClient(Long id) {
        return this.clientRepository.findOne(id)
                .orElseThrow(() -> new ControllerException(String.format("Client id not found: %d", id)));
    }

    /**
     * Returns the domain with a given id.
     *
     * @param id the id of the domain
     * @return the domain
     */
    public WebDomain getDomain(Long id) {
        return this.domainRepository.findOne(id)
                .orElseThrow(() -> new ControllerException(String.format("Domain id not found: %d", id)));
    }

    /**
     * Returns the rental with a given id.
     *
     * @param id the id of the rental
     * @return the rental
     */
    public Rental getRental(Long id) {
        return this.rentalRepository.findOne(id)
                .orElseThrow(() -> new ControllerException(String.format("Rental id not found: %d", id)));
    }

    /**
     * Add client to the client repository
     *
     * @param client
     * @throws ValidatorException
     */
    public void addClient(Client client) throws ValidatorException {
        this.clientRepository.save(client)
                .ifPresent(x -> {
                    throw new ControllerException(String.format("Client id already exists: %d", client.getId()));
                });
    }

    /**
     * Add domain to the domain repository
     *
     * @param webDomain
     * @throws ValidatorException
     */
    public void addDomain(WebDomain webDomain) throws ValidatorException {
        this.domainRepository.save(webDomain)
                .ifPresent(x -> {
                    throw new ControllerException(String.format("Domain id already exists: %d", webDomain.getId()));
                });
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
        this.clientRepository.findOne(rental.getClientId())
                .orElseThrow(() -> new ControllerException(String.format("Client id does not exist: %d", rental.getClientId())));
        this.domainRepository.findOne(rental.getDomainId())
                .orElseThrow(() -> new ControllerException(String.format("WebDomain id does not exist: %d", rental.getDomainId())));

        var rentalsWithCurrentDomainId = this.filterRentalsByDomainId(rental.getDomainId());
        rentalsWithCurrentDomainId.stream()
                .filter(rental1 -> !this.domainCanBeRented(rental1, rental))
                .findAny()
                .ifPresent(rental1 -> {
                    throw new ControllerException(String.format("WebDomain id is already rented: %d", rental.getDomainId()));
                });

        this.rentalRepository.save(rental)
                .ifPresent(x -> {
                    throw new ControllerException(String.format("Rental id already exists: %d", rental.getId()));
                });
    }

    private boolean domainCanBeRented(Rental oldRental, Rental newRental) {
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

        return newRentalStartDate.after(oldRentalEndDate);
    }

    private String getRentalEndDate(Rental rental) {
        var rentalStartDateString = rental.getStartDate();
        var rentalStartDateSplit = rentalStartDateString.split("-");
        return rentalStartDateSplit[0] + "-" +
                rentalStartDateSplit[1] + "-" +
                (Integer.parseInt(rentalStartDateSplit[2]) + rental.getDuration());
    }

    /**
     * Delete a client from the client repository.
     *
     * @param clientId the client's id to delete.
     */
    public void deleteClient(Long clientId) {
        Set<Rental> rentalsWithClientId = this.filterRentalsByClientId(clientId);
        rentalsWithClientId.forEach(rental -> {
            try {
                this.rentalRepository.delete(rental.getId());
            } catch (Exception e) {
                throw new ControllerException(e.getMessage());
            }
        });

        this.clientRepository.delete(clientId)
                .orElseThrow(() -> new ControllerException(String.format("Client id not found: %d", clientId)));
    }

    /**
     * Delete a domain from the domain repository.
     *
     * @param domainId the domain's id to delete.
     */
    public void deleteDomain(Long domainId) {
        Set<Rental> rentalsWithDomainId = this.filterRentalsByDomainId(domainId);
        rentalsWithDomainId.forEach(rental -> {
            try {
                this.rentalRepository.delete(rental.getId());
            } catch (Exception e) {
                throw new ControllerException(e.getMessage());
            }
        });

        this.domainRepository.delete(domainId)
                .orElseThrow(() -> new ControllerException(String.format("Domain id not found: %d", domainId)));
    }

    /**
     * Delete a rental from the rental repository.
     *
     * @param rentalId the rental's id to delete.
     */
    public void deleteRental(Long rentalId) {
        this.rentalRepository.delete(rentalId)
                .orElseThrow(() -> new ControllerException(String.format("Rental id not found: %d", rentalId)));
    }

    /**
     * Updates an existent client.
     *
     * @param client
     * @throws ValidatorException
     */
    public void updateClient(Client client) throws ValidatorException {
        this.clientRepository.update(client)
                .orElseThrow(() -> new ControllerException(String.format("(Update) Client id not found: %d", client.getId())));
    }

    /**
     * Updates an existent domain.
     *
     * @param webDomain
     * @throws ValidatorException
     */
    public void updateDomain(WebDomain webDomain) throws ValidatorException {
        this.domainRepository.update(webDomain)
                .orElseThrow(() -> new ControllerException(String.format("(Update) Domain id not found: %d", webDomain.getId())));
    }

    /**
     * Updates an existent rental.
     *
     * @param rental
     * @throws ValidatorException
     */
    public void updateRental(Rental rental) throws ValidatorException {
        this.rentalRepository.update(rental)
                .orElseThrow(() -> new ControllerException(String.format("(Update) Rental id not found: %d", rental.getId())));
    }

    /**
     * Returns all clients with a matching or a partial matching name.
     * @param name the client name
     * @return the set of clients with a matching or a partially matching name.
     */
    public Set<Client> filterClientByName(String name) {
        return StreamSupport.stream(this.clientRepository.findAll().spliterator(), false)
                .filter(client -> client.getName().contains(name))
                .collect(Collectors.toSet());
    }
  
    /**
     * Returns all domains with a matching or a partially matching name.
     * @param name the domain name
     * @return the set of domains with a matching or a partially matching name.
     */
    public Set<WebDomain> filterDomainByName(String name) {
        return StreamSupport.stream(this.domainRepository.findAll().spliterator(), false)
                .filter(domain -> domain.getName().contains(name))
                .collect(Collectors.toSet());
    }

    /**
     * Returns all rentals for a given client id.
     * @param id the client's id
     * @return the set of rentals a the matching client id.
     */
    public Set<Rental> filterRentalsByClientId(Long id) {
        return StreamSupport.stream(this.rentalRepository.findAll().spliterator(), false)
                .filter(rental -> rental.getClientId().equals(id))
                .collect(Collectors.toSet());
    }

    /**
     * Returns all rentals for a given web domain id.
     * @param id the web domain's id.
     * @return the set of rentals of the given web domain.
     */
    public Set<Rental> filterRentalsByDomainId(Long id) {
        return StreamSupport.stream(this.rentalRepository.findAll().spliterator(), false)
                .filter(rental -> rental.getDomainId().equals(id))
                .collect(Collectors.toSet());
    }

    /**
     * Returns all rentals for a given client name.
     * @param name the client's name
     * @return the set of rentals a the matching client name.
     */
    public Set<Rental> filterRentalsByClientName(String name) {
        return this.filterClientByName(name).stream()
                .map(i -> StreamSupport.stream(this.rentalRepository.findAll().spliterator(), false)
                        .filter(rental -> rental.getClientId().equals(i.getId()))
                        .collect(Collectors.toSet())
                )
                .reduce(new HashSet<>(), (rentals1, rentals2) -> {
                    rentals1.addAll(rentals2);
                    return rentals1;
                });
    }

    /**
     * Returns all rentals for a given domain name.
     * @param name the domain's name
     * @return the set of rentals a the matching domain name.
     */
    public Set<Rental> filterRentalsByDomainName(String name) {
        return this.filterDomainByName(name).stream()
                .map(i -> StreamSupport.stream(this.rentalRepository.findAll().spliterator(), false)
                        .filter(rental -> rental.getDomainId().equals(i.getId()))
                        .collect(Collectors.toSet())
                )
                .reduce(new HashSet<>(), (rentals1, rentals2) -> {
                    rentals1.addAll(rentals2);
                    return rentals1;
                });
    }

    /**
     * Returns clients with most rentals
     * @return the set of clients with most rentals
     */
    public Set<Client> getMostRentingClients() {
        Iterable<Rental> rentals = this.rentalRepository.findAll();
        Map<Client, Integer> rentingClientsOccurrences = StreamSupport.stream(rentals.spliterator(), false)
                .collect(Collectors.toMap(rental -> {
                    try {
                        return this.clientRepository.findOne(rental.getClientId()).orElse(null);
                    } catch (Exception e) {
                        throw new ControllerException(e.getMessage());
                    }
                }, client -> 1, Integer::sum));

        int mostRentals = rentingClientsOccurrences.values().stream()
                .mapToInt(occurrences -> occurrences)
                .max()
                .orElse(0);

        return rentingClientsOccurrences.entrySet().stream()
                .filter(clientOccurrences -> clientOccurrences.getValue() == mostRentals)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
  
    /**
     * Returns the most rented domains.
     * @return the set of the most rented domains.
     */
    public Set<WebDomain> getMostRentedDomains() {
        Iterable<Rental> rentals = this.rentalRepository.findAll();
        Map<WebDomain, Integer> rentedDomainsOccurrences = StreamSupport.stream(rentals.spliterator(), false)
                .collect(Collectors.toMap(rental -> {
                    try {
                        return this.domainRepository.findOne(rental.getDomainId()).orElse(null);
                    } catch (Exception e) {
                        throw new ControllerException(e.getMessage());
                    }
                }, domain -> 1, Integer::sum));

        int maxOccurrences = rentedDomainsOccurrences.values().stream()
                .mapToInt(occurrences -> occurrences)
                .max()
                .orElse(0);

        return rentedDomainsOccurrences.entrySet().stream()
                .filter(domainOccurrence -> domainOccurrence.getValue() == maxOccurrences)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
  
    /**
     * Returns the years with the most rentals.
     * @return the set of years.
     */
    public Set<String> getMostRentedYears() {
        Iterable<Rental> rentals = this.rentalRepository.findAll();
        Map<String, Integer> rentedYearsOccurences = StreamSupport.stream(rentals.spliterator(), false)
                .collect(Collectors.toMap(rental -> rental.getStartDate().split("-")[2], rental -> 1, Integer::sum));

        int maxOccurrences = rentedYearsOccurences.values().stream()
                .mapToInt(occurrences -> occurrences)
                .max()
                .orElse(0);

        return rentedYearsOccurences.entrySet().stream()
                .filter(occurences -> occurences.getValue() == maxOccurrences)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
    /**
     * Returns the most rented TLD
     * @return the set of most rented TLD
     */
    public Set<String> getMostCommonTld() {
        Iterable<Rental> rentals = this.rentalRepository.findAll();
        Map<String, Integer> rentedTldOccurrences = StreamSupport.stream(rentals.spliterator(), false)
                .collect(Collectors.toMap(rental -> {
                            try {
                                return this.domainRepository.findOne(rental.getDomainId()).get().getName()
                                        .split("\\.")[this.domainRepository.findOne(rental.getDomainId()).get().getName().split("\\.").length - 1];
                            } catch (Exception e) {
                                throw new ControllerException(e.getMessage());
                            }
                        }
                        ,domain -> 1, Integer::sum));

        int maxOccurrences = rentedTldOccurrences.values().stream()
                .mapToInt(occurrences -> occurrences)
                .max()
                .orElse(0);

        return rentedTldOccurrences.entrySet().stream()
                .filter(tldOccurrence -> tldOccurrence.getValue() == maxOccurrences)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
}
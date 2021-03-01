package controller;

import exceptions.ControllerException;
import model.Client;
import model.Domain;
import model.Rental;
import model.validators.ValidatorException;
import repository.Repository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Controller {
    private Repository<Long, Client> clientRepository;
    private Repository<Long, Domain> domainRepository;
    private Repository<Long, Rental> rentalRepository;

    public Controller(Repository<Long, Client> clientRepository, Repository<Long, Domain> domainRepository, Repository<Long, Rental> rentalRepository) {
        this.clientRepository = clientRepository;
        this.domainRepository = domainRepository;
        this.rentalRepository = rentalRepository;
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
    public Set<Domain> getDomains() {
        Iterable<Domain> domains = this.domainRepository.findAll();
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
     * @throws ControllerException if the id is not found
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
     * @throws ControllerException if the id is not found
     */
    public Domain getDomain(Long id) {
        return this.domainRepository.findOne(id)
                .orElseThrow(() -> new ControllerException(String.format("Domain id not found: %d", id)));
    }

    /**
     * Returns the rental with a given id.
     *
     * @param id the id of the rental
     * @return the rental
     * @throws ControllerException if the id is not found
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
        clientRepository.save(client);
    }

    /**
     * Add domain to the domain repository
     *
     * @param domain
     * @throws ValidatorException
     */
    public void addDomain(Domain domain) throws ValidatorException {
        domainRepository.save(domain);
    }

    /**
     * Add domain to the rental repository
     *
     * @param rental
     * @throws ValidatorException
     */
    public void addRental(Rental rental) throws ValidatorException {
        rentalRepository.save(rental);
    }

    /**
     * Delete a client from the client repository.
     *
     * @param clientId the client's id to delete.
     */
    public void deleteClient(Long clientId) {
        clientRepository.delete(clientId);
    }

    /**
     * Delete a domain from the domain repository.
     *
     * @param domainId the domain's id to delete.
     */
    public void deleteDomain(Long domainId) {
        domainRepository.delete(domainId);
    }

    /**
     * Delete a rental from the rental repository.
     *
     * @param rentalId the rental's id to delete.
     */
    public void deleteRental(Long rentalId) {
        rentalRepository.delete(rentalId);
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
     * @param domain
     * @throws ValidatorException
     */
    public void updateDomain(Domain domain) throws ValidatorException {
        this.domainRepository.update(domain)
                .orElseThrow(() -> new ControllerException(String.format("(Update) Domain id not found: %d", domain.getId())));
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
}
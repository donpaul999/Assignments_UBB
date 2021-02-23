package controller;

import model.Client;
import model.Domain;
import model.Rental;
import model.validators.ValidatorException;
import repository.InMemoryRepository;
import repository.Repository;

import java.util.Base64;

public class Controller {
    private Repository<Long, Client> clientRepository;
    private Repository<Long, Domain> domainRepository;
    private Repository<Long, Rental> rentalRepository;

    /**
     * Add client to the client repository
     * @param client
     * @throws ValidatorException
     */
    public void addClient(Client client) throws ValidatorException {
        clientRepository.save(client);
    }

    /**
     * Add domain to the domain repository
     * @param domain
     * @throws ValidatorException
     */
    public void addDomain(Domain domain) throws ValidatorException {
        domainRepository.save(domain);
    }

    /**
     * Add domain to the rental repository
     * @param rental
     * @throws ValidatorException
     */
    public void addRental(Rental rental) throws ValidatorException {
        rentalRepository.save(rental);
    }


}

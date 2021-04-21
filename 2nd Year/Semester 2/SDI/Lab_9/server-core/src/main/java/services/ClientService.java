package services;

import exceptions.ControllerException;
import model.Client;
import model.Rental;
import model.validators.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.database.ClientDatabaseRepository;
import repository.database.RentalDatabaseRepository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ClientService implements IClientService{
    public static final Logger log = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    private ClientDatabaseRepository clients;

    @Autowired
    private RentalDatabaseRepository rentals;

    /**
     * Returns all the clients.
     *
     * @return the set of clients
     */
    public Set<Client> getClients() {
        log.trace("getClients - method entered");

        Iterable<Client> clients = this.clients.findAll();
        Set<Client> result = StreamSupport.stream(clients.spliterator(), false).collect(Collectors.toSet());

        log.trace("getClients - method finished: result={}", result);

        return result;
    }

    /**
     * Returns the client with a given id.
     *
     * @param id the id of the client
     * @return the client
     */
    public Client getClient(Long id) {
        log.trace("getClient - method entered: id={}", id);

        Client result = this.clients.findById(id)
                .orElseThrow(() -> new ControllerException(String.format("Client id not found: %d", id)));

        log.trace("getClient - method finished: result={}", result);

        return result;
    }

    /**
     * Add client to the client repository
     *
     * @param client
     * @throws ValidatorException
     */
    public void addClient(Client client) throws ValidatorException {
        log.trace("addClient - method entered: client={}", client);

        this.clients.findById(client.getId()).ifPresent(x -> {
            throw new ControllerException(String.format("Client id already exists: %d", client.getId()));
        });

        this.clients.save(client);

        log.trace("addClient - method finished");
    }

    /**
     * Delete a client from the client repository.
     *
     * @param id the client's id to delete.
     */
    public void deleteClient(Long id) {
        log.trace("deleteClient - method entered: id={}", id);

        Set<Rental> rentalsWithClientId = this.rentals.findRentalsByClientId(id);
        rentalsWithClientId.forEach(rental -> {
            try {
                this.rentals.deleteById(rental.getId());
            } catch (Exception e) {
                throw new ControllerException(e.getMessage());
            }
        });

        this.clients.findById(id)
                .orElseThrow(() -> new ControllerException(String.format("Client id not found: %d", id)));

        this.clients.deleteById(id);

        log.trace("deleteClient - method finished");
    }

    /**
     * Updates an existent client.
     *
     * @param client
     * @throws ValidatorException
     */
    public void updateClient(Client client) throws ValidatorException {
        log.trace("updateClient - method entered: client={}", client);

        this.clients.findById(client.getId())
                .orElseThrow(() -> new ControllerException(String.format("(Update) Client id not found: %d", client.getId())));

        this.clients.save(client);

        log.trace("updateClient - method finished");
    }

    /**
     * Returns all clients with a matching or a partial matching name.
     * @param name the client name
     * @return the set of clients with a matching or a partially matching name.
     */
    public Set<Client> filterClientsByName(String name) {
        log.trace("filterClientByName - method entered: name={}", name);
        var clients = this.clients.findByNameContaining(name);
        log.trace("filterClientByName - method finished: result={}", clients);

        return clients;
    }

    /**
     * Returns all clients sorted by name in ascending order.
     * @return the set of clients
     */
    public Set<Client> sortAscendingByClientName() {
        log.trace("sortAscendingByName - method entered");
        var clients = this.clients.findAllByOrderByNameAsc();
        log.trace("sortAscendingByName - method finished: result={}", clients);

        return clients;
    }
}

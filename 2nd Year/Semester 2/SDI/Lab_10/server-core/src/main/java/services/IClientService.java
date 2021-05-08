package services;

import model.Client;
import model.validators.ValidatorException;

import java.util.Set;
public interface IClientService {
    /**
     * Returns all the clients.
     *
     * @return the set of clients
     */
    Set<Client> getClients();

    /**
     * Returns the client with a given id.
     *
     * @param id the id of the client
     * @return the client
     */
    Client getClient(Long id);

    /**
     * Add client to the client repository
     *
     * @param client
     * @throws ValidatorException
     */
    void addClient(Client client) throws ValidatorException ;

    /**
     * Delete a client from the client repository.
     *
     * @param clientId the client's id to delete.
     */
    void deleteClient(Long clientId);


    /**
     * Updates an existent client.
     *
     * @param client
     * @throws ValidatorException
     */
    void updateClient(Client client) throws ValidatorException;

    /**
     * Returns all clients with a matching or a partial matching name.
     * @param name the client name
     * @return the set of clients with a matching or a partially matching name.
     */
    Set<Client> filterClientsByName(String name);

    /**
     * Returns all clients sorted by name.
     * @return the set of clients sorted by name.
     */
    Set<Client> sortAscendingByClientName();
}

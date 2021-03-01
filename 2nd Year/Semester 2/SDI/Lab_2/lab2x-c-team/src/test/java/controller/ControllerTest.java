package controller;

import exceptions.ControllerException;
import model.Client;
import model.Domain;
import model.Rental;
import model.validators.ClientValidator;
import model.validators.DomainValidator;
import model.validators.RentalValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.InMemoryRepository;
import repository.Repository;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private static final Long CLIENT_ID = 1L;
    private static final Long DOMAIN_ID = 1L;
    private static final Long RENTAL_ID = 1L;

    private static final String CLIENT_NAME = "client";
    private static final Boolean CLIENT_BUSINESS = false;

    private static final String DOMAIN_NAME = "cteam.org";
    private static final Integer DOMAIN_PRICE = 100;

    private static final Integer RENTAL_DURATION = 15;
    private static final String RENTAL_DATE = "02-10-2020";

    private static final String UPDATED_CLIENT_NAME = "updatedClient";
    private static final Boolean UPDATED_CLIENT_BUSINESS = true;
    private static final String UPDATED_DOMAIN_NAME = "updatedDomain.com";
    private static final Integer UPDATED_DOMAIN_PRICE = 200;
    private static final Integer UPDATED_RENTAL_DURATION = 30;
    public static final String UPDATED_RENTAL_DATE = "02-10-2019";

    private static Repository<Long, Client> clientRepository;
    private static Repository<Long, Domain> domainRepository;
    private static Repository<Long, Rental> rentalRepository;
    private static Controller controller;

    @BeforeEach
    public void setUp() {
        clientRepository = new InMemoryRepository<>(new ClientValidator());
        domainRepository = new InMemoryRepository<>(new DomainValidator());
        rentalRepository = new InMemoryRepository<>(new RentalValidator());

        controller = new Controller(clientRepository, domainRepository, rentalRepository);
    }

    @AfterEach
    public void tearDown() {
        controller = null;
    }

    @Test
    void getClients() {
        Client client1 = new Client(CLIENT_NAME, CLIENT_BUSINESS);
        client1.setId(CLIENT_ID);
        clientRepository.save(client1);
        Client client2 = new Client(CLIENT_NAME + "two", CLIENT_BUSINESS);
        client2.setId(CLIENT_ID + 1);
        clientRepository.save(client2);

        Set<Client> clients = controller.getClients();
        assertEquals(2, clients.size());
        assertTrue(clients.contains(client1));
        assertTrue(clients.contains(client2));
    }

    @Test
    void getDomains() {
        Domain domain1 = new Domain(DOMAIN_NAME, DOMAIN_PRICE);
        domain1.setId(DOMAIN_ID);
        domainRepository.save(domain1);
        Domain domain2 = new Domain(DOMAIN_NAME + "2", DOMAIN_PRICE);
        domain2.setId(DOMAIN_ID + 1);
        domainRepository.save(domain2);

        Set<Domain> domains = controller.getDomains();
        assertEquals(2, domains.size());
        assertTrue(domains.contains(domain1));
        assertTrue(domains.contains(domain2));
    }

    @Test
    void getRentals() {
        Client client = new Client(CLIENT_NAME, CLIENT_BUSINESS);
        client.setId(CLIENT_ID);
        clientRepository.save(client);

        Domain domain = new Domain(DOMAIN_NAME, DOMAIN_PRICE);
        domain.setId(DOMAIN_ID);
        domainRepository.save(domain);

        Rental rental = new Rental(CLIENT_ID, DOMAIN_ID, RENTAL_DATE ,RENTAL_DURATION);
        rental.setId(RENTAL_ID);
        rentalRepository.save(rental);

        Set<Rental> rentals = controller.getRentals();
        assertEquals(1, rentals.size());
        assertTrue(rentals.contains(rental));
    }

    @Test
    void getClient() {
        Client client = new Client(CLIENT_NAME, CLIENT_BUSINESS);
        client.setId(CLIENT_ID);
        clientRepository.save(client);

        Client clientGet = controller.getClient(CLIENT_ID);
        assertEquals(client, clientGet);
        assertThrows(ControllerException.class, () -> controller.getClient(CLIENT_ID + 1));
    }

    @Test
    void getDomain() {
        Domain domain = new Domain(DOMAIN_NAME, DOMAIN_PRICE);
        domain.setId(DOMAIN_ID);
        domainRepository.save(domain);

        Domain domainGet = controller.getDomain(DOMAIN_ID);
        assertEquals(domain, domainGet);
        assertThrows(ControllerException.class, () -> controller.getDomain(DOMAIN_ID + 1));
    }

    @Test
    void getRental() {
        Client client = new Client(CLIENT_NAME, CLIENT_BUSINESS);
        client.setId(CLIENT_ID);
        clientRepository.save(client);

        Domain domain = new Domain(DOMAIN_NAME, DOMAIN_PRICE);
        domain.setId(DOMAIN_ID);
        domainRepository.save(domain);

        Rental rental = new Rental(CLIENT_ID, DOMAIN_ID, RENTAL_DATE ,RENTAL_DURATION);
        rental.setId(RENTAL_ID);
        rentalRepository.save(rental);

        Rental rentalGet = controller.getRental(RENTAL_ID);
        assertEquals(rental, rentalGet);
        assertThrows(ControllerException.class, () -> controller.getRental(RENTAL_ID + 1));
    }

    @Test
    void addClient() {
        Client client = new Client(CLIENT_NAME, CLIENT_BUSINESS);
        client.setId(CLIENT_ID);
        controller.addClient(client);

        client = controller.getClient(CLIENT_ID);
        assertEquals(client.getName(), CLIENT_NAME);
    }

    @Test
    void addDomain() {
        Domain domain = new Domain(DOMAIN_NAME, DOMAIN_PRICE);
        domain.setId(DOMAIN_ID);
        controller.addDomain(domain);

        domain = controller.getDomain(DOMAIN_ID);
        assertEquals(domain.getName(), DOMAIN_NAME);
    }

    @Test
    void addRental() {
        Client client = new Client(CLIENT_NAME, CLIENT_BUSINESS);
        client.setId(CLIENT_ID);
        controller.addClient(client);

        Domain domain = new Domain(DOMAIN_NAME, DOMAIN_PRICE);
        domain.setId(DOMAIN_ID);
        controller.addDomain(domain);

        Rental rental = new Rental(CLIENT_ID, DOMAIN_ID, RENTAL_DATE ,RENTAL_DURATION);
        rental.setId(RENTAL_ID);
        controller.addRental(rental);

        rental = controller.getRental(RENTAL_ID);
        assertEquals(rental.getClientId(), client.getId());
    }
    
    @Test
    void deleteClient() {
        Client client = new Client(CLIENT_NAME, CLIENT_BUSINESS);
        client.setId(CLIENT_ID);
        clientRepository.save(client);

        controller.deleteClient(client.getId());
        assertThrows(ControllerException.class, () -> controller.getClient(client.getId()));
    }

    @Test
    void deleteDomain() {
        Domain domain = new Domain(DOMAIN_NAME, DOMAIN_PRICE);
        domain.setId(DOMAIN_ID);
        domainRepository.save(domain);

        controller.deleteDomain(domain.getId());
        assertThrows(ControllerException.class, () -> controller.getDomain(domain.getId()));
    }

    @Test
    void deleteRental() {
        Client client = new Client(CLIENT_NAME, CLIENT_BUSINESS);
        client.setId(CLIENT_ID);
        clientRepository.save(client);

        Domain domain = new Domain(DOMAIN_NAME, DOMAIN_PRICE);
        domain.setId(DOMAIN_ID);
        domainRepository.save(domain);

        Rental rental = new Rental(CLIENT_ID, DOMAIN_ID, RENTAL_DATE, RENTAL_DURATION);
        rental.setId(RENTAL_ID);
        rentalRepository.save(rental);

        controller.deleteRental(rental.getId());

        Client sameClientFromRepository = controller.getClient(client.getId());
        Domain sameDomainFromRepository = controller.getDomain(domain.getId());
        assertEquals(client, sameClientFromRepository);
        assertEquals(domain, sameDomainFromRepository);

        assertThrows(ControllerException.class, () -> controller.getRental(rental.getId()));
    }
  
    @Test
    void updateClient() {
        Client clientToUpdate = new Client(CLIENT_NAME, CLIENT_BUSINESS);
        clientToUpdate.setId(CLIENT_ID);
        controller.addClient(clientToUpdate);

        Client updatedClient = new Client(UPDATED_CLIENT_NAME, UPDATED_CLIENT_BUSINESS);
        updatedClient.setId(CLIENT_ID);
        controller.updateClient(updatedClient);

        updatedClient = controller.getClient(CLIENT_ID);
        assertEquals(updatedClient.getName(), UPDATED_CLIENT_NAME);
        assertEquals(updatedClient.isBusiness(), UPDATED_CLIENT_BUSINESS);
    }

    @Test
    void updateDomain() {
        Domain domain = new Domain (DOMAIN_NAME, DOMAIN_PRICE);
        domain.setId(DOMAIN_ID);
        controller.addDomain(domain);

        Domain updatedDomain = new Domain(UPDATED_DOMAIN_NAME, UPDATED_DOMAIN_PRICE);
        updatedDomain.setId(DOMAIN_ID);
        controller.updateDomain(updatedDomain);

        updatedDomain = controller.getDomain(DOMAIN_ID);
        assertEquals(updatedDomain.getName(), UPDATED_DOMAIN_NAME);
        assertEquals(updatedDomain.getPrice(), UPDATED_DOMAIN_PRICE);
    }

    @Test
    void updateRental() {
        Client client = new Client (CLIENT_NAME, CLIENT_BUSINESS);
        client.setId(CLIENT_ID);
        controller.addClient(client);

        Domain domain = new Domain (DOMAIN_NAME, DOMAIN_PRICE);
        domain.setId(DOMAIN_ID);
        controller.addDomain(domain);

        Rental rental = new Rental(CLIENT_ID, DOMAIN_ID, RENTAL_DATE, RENTAL_DURATION);
        rental.setId(RENTAL_ID);
        controller.addRental(rental);

        Rental updatedRental = new Rental(CLIENT_ID, DOMAIN_ID, UPDATED_RENTAL_DATE, UPDATED_RENTAL_DURATION);
        updatedRental.setId(RENTAL_ID);
        controller.updateRental(updatedRental);

        updatedRental = controller.getRental(RENTAL_ID);
        assertEquals(updatedRental.getStartDate(), UPDATED_RENTAL_DATE);
        assertEquals(updatedRental.getDuration(), UPDATED_RENTAL_DURATION);
    }
}
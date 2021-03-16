package controller;

import exceptions.ControllerException;
import model.Client;
import model.Rental;
import model.WebDomain;
import model.validators.ClientValidator;
import model.validators.RentalValidator;
import model.validators.WebDomainValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.InMemoryRepository;
import repository.Repository;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private static final Long CLIENT_ID = 1L;
    private static final Long DOMAIN_ID = 1L;
    private static final Long RENTAL_ID = 1L;

    private static final Long CLIENT_ID_2 = 2L;
    private static final Long DOMAIN_ID_2 = 2L;
    private static final Long RENTAL_ID_2 = 2L;

    private static final Long RENTAL_ID_3 = 3L;

    private static final String CLIENT_NAME = "One Name";
    private static final Boolean CLIENT_BUSINESS = false;

    private static final String CLIENT_NAME_2 = "Another Name";
    private static final Boolean CLIENT_BUSINESS_2 = false;

    private static final String DOMAIN_NAME = "cteam.org";
    private static final Integer DOMAIN_PRICE = 100;

    private static final Integer RENTAL_DURATION = 15;
    private static final String RENTAL_DATE = "02-10-2020";
    private static final String RENTAL_DATE_2 = "02-10-2019";
    private static final String RENTAL_DATE_3 = "02-10-2018";

    private static final String UPDATED_CLIENT_NAME = "Updated Name";
    private static final Boolean UPDATED_CLIENT_BUSINESS = true;
    private static final String UPDATED_DOMAIN_NAME = "updatedDomain.com";
    private static final Integer UPDATED_DOMAIN_PRICE = 200;
    private static final Integer UPDATED_RENTAL_DURATION = 30;
    public static final String UPDATED_RENTAL_DATE = "02-10-2019";

    private static Repository<Long, Client> clientRepository;
    private static Repository<Long, WebDomain> domainRepository;
    private static Repository<Long, Rental> rentalRepository;
    private static Controller controller;

    @BeforeEach
    public void setUp() {
        clientRepository = new InMemoryRepository<>(new ClientValidator());
        domainRepository = new InMemoryRepository<>(new WebDomainValidator());
        rentalRepository = new InMemoryRepository<>(new RentalValidator());

        controller = new Controller(clientRepository, domainRepository, rentalRepository);
    }

    @AfterEach
    public void tearDown() {
        controller = null;
    }

    @Test
    void getClients() throws SQLException {
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
    void getDomains() throws SQLException {
        WebDomain webDomain1 = new WebDomain(DOMAIN_NAME, DOMAIN_PRICE);
        webDomain1.setId(DOMAIN_ID);
        domainRepository.save(webDomain1);
        WebDomain webDomain2 = new WebDomain(DOMAIN_NAME + "2", DOMAIN_PRICE);
        webDomain2.setId(DOMAIN_ID + 1);
        domainRepository.save(webDomain2);

        Set<WebDomain> webDomains = controller.getDomains();
        assertEquals(2, webDomains.size());
        assertTrue(webDomains.contains(webDomain1));
        assertTrue(webDomains.contains(webDomain2));
    }

    @Test
    void getRentals() throws SQLException {
        Client client = new Client(CLIENT_NAME, CLIENT_BUSINESS);
        client.setId(CLIENT_ID);
        clientRepository.save(client);

        WebDomain webDomain = new WebDomain(DOMAIN_NAME, DOMAIN_PRICE);
        webDomain.setId(DOMAIN_ID);
        domainRepository.save(webDomain);

        Rental rental = new Rental(CLIENT_ID, DOMAIN_ID, RENTAL_DATE ,RENTAL_DURATION);
        rental.setId(RENTAL_ID);
        rentalRepository.save(rental);

        Set<Rental> rentals = controller.getRentals();
        assertEquals(1, rentals.size());
        assertTrue(rentals.contains(rental));
    }

    @Test
    void getClient() throws SQLException {
        Client client = new Client(CLIENT_NAME, CLIENT_BUSINESS);
        client.setId(CLIENT_ID);
        clientRepository.save(client);

        Client clientGet = controller.getClient(CLIENT_ID);
        assertEquals(client, clientGet);
        assertThrows(ControllerException.class, () -> controller.getClient(CLIENT_ID + 1));
    }

    @Test
    void getDomain() throws SQLException {
        WebDomain webDomain = new WebDomain(DOMAIN_NAME, DOMAIN_PRICE);
        webDomain.setId(DOMAIN_ID);
        domainRepository.save(webDomain);

        WebDomain webDomainGet = controller.getDomain(DOMAIN_ID);
        assertEquals(webDomain, webDomainGet);
        assertThrows(ControllerException.class, () -> controller.getDomain(DOMAIN_ID + 1));
    }

    @Test
    void getRental() throws SQLException {
        Client client = new Client(CLIENT_NAME, CLIENT_BUSINESS);
        client.setId(CLIENT_ID);
        clientRepository.save(client);

        WebDomain webDomain = new WebDomain(DOMAIN_NAME, DOMAIN_PRICE);
        webDomain.setId(DOMAIN_ID);
        domainRepository.save(webDomain);

        Rental rental = new Rental(CLIENT_ID, DOMAIN_ID, RENTAL_DATE ,RENTAL_DURATION);
        rental.setId(RENTAL_ID);
        rentalRepository.save(rental);

        Rental rentalGet = controller.getRental(RENTAL_ID);
        assertEquals(rental, rentalGet);
        assertThrows(ControllerException.class, () -> controller.getRental(RENTAL_ID + 1));
    }

    @Test
    void addClient() throws SQLException {
        Client client = new Client(CLIENT_NAME, CLIENT_BUSINESS);
        client.setId(CLIENT_ID);
        controller.addClient(client);

        client = controller.getClient(CLIENT_ID);
        assertEquals(client.getName(), CLIENT_NAME);
    }

    @Test
    void addDomain() throws SQLException {
        WebDomain webDomain = new WebDomain(DOMAIN_NAME, DOMAIN_PRICE);
        webDomain.setId(DOMAIN_ID);
        controller.addDomain(webDomain);

        webDomain = controller.getDomain(DOMAIN_ID);
        assertEquals(webDomain.getName(), DOMAIN_NAME);
    }

    @Test
    void addRental() throws SQLException {
        Client client = new Client(CLIENT_NAME, CLIENT_BUSINESS);
        client.setId(CLIENT_ID);
        controller.addClient(client);

        WebDomain webDomain = new WebDomain(DOMAIN_NAME, DOMAIN_PRICE);
        webDomain.setId(DOMAIN_ID);
        controller.addDomain(webDomain);

        Rental rental = new Rental(CLIENT_ID, DOMAIN_ID, RENTAL_DATE ,RENTAL_DURATION);
        rental.setId(RENTAL_ID);
        controller.addRental(rental);

        rental = controller.getRental(RENTAL_ID);
        assertEquals(rental.getClientId(), client.getId());
    }
    
    @Test
    void deleteClient() throws SQLException {
        Client client = new Client(CLIENT_NAME, CLIENT_BUSINESS);
        client.setId(CLIENT_ID);
        clientRepository.save(client);

        WebDomain webDomain = new WebDomain(DOMAIN_NAME, DOMAIN_PRICE);
        webDomain.setId(DOMAIN_ID);
        domainRepository.save(webDomain);

        Rental rental = new Rental(client.getId(), webDomain.getId(), RENTAL_DATE, RENTAL_DURATION);
        rental.setId(RENTAL_ID);
        rentalRepository.save(rental);

        controller.deleteClient(client.getId());
        assertThrows(ControllerException.class, () -> controller.getClient(client.getId()));
        assertThrows(ControllerException.class, () -> controller.getRental(rental.getId()));
    }

    @Test
    void deleteDomain() throws SQLException {
        Client client = new Client(CLIENT_NAME, CLIENT_BUSINESS);
        client.setId(CLIENT_ID);
        clientRepository.save(client);

        WebDomain webDomain = new WebDomain(DOMAIN_NAME, DOMAIN_PRICE);
        webDomain.setId(DOMAIN_ID);
        domainRepository.save(webDomain);

        Rental rental = new Rental(client.getId(), webDomain.getId(), RENTAL_DATE, RENTAL_DURATION);
        rental.setId(RENTAL_ID);
        rentalRepository.save(rental);

        controller.deleteDomain(webDomain.getId());
        assertThrows(ControllerException.class, () -> controller.getDomain(webDomain.getId()));
        assertThrows(ControllerException.class, () -> controller.getRental(rental.getId()));
    }

    @Test
    void deleteRental() throws SQLException {
        Client client = new Client(CLIENT_NAME, CLIENT_BUSINESS);
        client.setId(CLIENT_ID);
        clientRepository.save(client);

        WebDomain webDomain = new WebDomain(DOMAIN_NAME, DOMAIN_PRICE);
        webDomain.setId(DOMAIN_ID);
        domainRepository.save(webDomain);

        Rental rental = new Rental(CLIENT_ID, DOMAIN_ID, RENTAL_DATE, RENTAL_DURATION);
        rental.setId(RENTAL_ID);
        rentalRepository.save(rental);

        controller.deleteRental(rental.getId());

        Client sameClientFromRepository = controller.getClient(client.getId());
        WebDomain sameWebDomainFromRepository = controller.getDomain(webDomain.getId());
        assertEquals(client, sameClientFromRepository);
        assertEquals(webDomain, sameWebDomainFromRepository);

        assertThrows(ControllerException.class, () -> controller.getRental(rental.getId()));
    }
  
    @Test
    void updateClient() throws SQLException {
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
    void updateDomain() throws SQLException {
        WebDomain webDomain = new WebDomain(DOMAIN_NAME, DOMAIN_PRICE);
        webDomain.setId(DOMAIN_ID);
        controller.addDomain(webDomain);

        WebDomain updatedWebDomain = new WebDomain(UPDATED_DOMAIN_NAME, UPDATED_DOMAIN_PRICE);
        updatedWebDomain.setId(DOMAIN_ID);
        controller.updateDomain(updatedWebDomain);

        updatedWebDomain = controller.getDomain(DOMAIN_ID);
        assertEquals(updatedWebDomain.getName(), UPDATED_DOMAIN_NAME);
        assertEquals(updatedWebDomain.getPrice(), UPDATED_DOMAIN_PRICE);
    }

    @Test
    void updateRental() throws SQLException {
        Client client = new Client (CLIENT_NAME, CLIENT_BUSINESS);
        client.setId(CLIENT_ID);
        controller.addClient(client);

        WebDomain webDomain = new WebDomain(DOMAIN_NAME, DOMAIN_PRICE);
        webDomain.setId(DOMAIN_ID);
        controller.addDomain(webDomain);

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

    @Test
    void getMostRentingClients() throws SQLException {
        Client client = new Client (CLIENT_NAME, CLIENT_BUSINESS);
        client.setId(CLIENT_ID);
        controller.addClient(client);

        Client client2 = new Client (CLIENT_NAME_2, CLIENT_BUSINESS_2);
        client2.setId(CLIENT_ID_2);
        controller.addClient(client2);

        WebDomain webDomain = new WebDomain(DOMAIN_NAME, DOMAIN_PRICE);
        webDomain.setId(DOMAIN_ID);
        controller.addDomain(webDomain);

        Rental rental = new Rental(CLIENT_ID, DOMAIN_ID, RENTAL_DATE, RENTAL_DURATION);
        rental.setId(RENTAL_ID);
        controller.addRental(rental);

        Rental rental2 = new Rental(CLIENT_ID_2, DOMAIN_ID, RENTAL_DATE_2, RENTAL_DURATION);
        rental2.setId(RENTAL_ID_2);
        controller.addRental(rental2);

        Rental rental3 = new Rental(CLIENT_ID_2, DOMAIN_ID, RENTAL_DATE_3, RENTAL_DURATION);
        rental3.setId(RENTAL_ID_3);
        controller.addRental(rental3);

        Set<Client> mostRentingClients = controller.getMostRentingClients();
        assertEquals(mostRentingClients.contains(client), false);
        assertEquals(mostRentingClients.contains(client2), true);
    }
  
    @Test
    void getMostRentedDomains() throws SQLException {
        Set<WebDomain> mostRentedWebDomains = new HashSet<>();
        // rentals: none => none
        assertEquals(controller.getMostRentedDomains(), mostRentedWebDomains);

        Client client1 = new Client(CLIENT_NAME, CLIENT_BUSINESS);
        client1.setId(CLIENT_ID);
        controller.addClient(client1);

        Client client2 = new Client(CLIENT_NAME + " Andrei", CLIENT_BUSINESS);
        client2.setId(CLIENT_ID + 1);
        controller.addClient(client2);

        WebDomain webDomain1 = new WebDomain(DOMAIN_NAME, DOMAIN_PRICE);
        webDomain1.setId(DOMAIN_ID);
        controller.addDomain(webDomain1);

        WebDomain webDomain2 = new WebDomain(DOMAIN_NAME + "a", DOMAIN_PRICE);
        webDomain2.setId(DOMAIN_ID + 1);
        controller.addDomain(webDomain2);

        Rental rental1 = new Rental(client1.getId(), webDomain1.getId(), RENTAL_DATE, RENTAL_DURATION);
        rental1.setId(RENTAL_ID);
        controller.addRental(rental1);

        mostRentedWebDomains.add(webDomain1);
        // rentals: r1(c1, d1) => domain1
        assertEquals(controller.getMostRentedDomains(), mostRentedWebDomains);

        Rental rental2 = new Rental(client2.getId(), webDomain1.getId(), RENTAL_DATE, RENTAL_DURATION);
        rental2.setId(RENTAL_ID + 1);
        controller.addRental(rental2);
        // rentals: r1(c1, d1), r2(c2, d1) => d1
        assertEquals(controller.getMostRentedDomains(), mostRentedWebDomains);

        Rental rental3 = new Rental(client1.getId(), webDomain2.getId(), RENTAL_DATE, RENTAL_DURATION);
        rental3.setId(RENTAL_ID + 2);
        controller.addRental(rental3);
        // rentals: r1(c1, d1), r2(c2, d1), r3(c1, d2) => d1
        assertEquals(controller.getMostRentedDomains(), mostRentedWebDomains);
    }

    @Test
    void filterDomainsByName() throws SQLException {
        WebDomain webDomain = new WebDomain(DOMAIN_NAME, DOMAIN_PRICE);
        webDomain.setId(DOMAIN_ID);
        controller.addDomain(webDomain);

        Set<WebDomain> webDomains = controller.filterDomainByName(DOMAIN_NAME);

        assertEquals(1, webDomains.size());
        assertEquals(webDomains.iterator().next(), webDomain);
    }
  
    @Test
    void filterClientsByName() throws SQLException {
        Client client = new Client (CLIENT_NAME, CLIENT_BUSINESS);
        client.setId(CLIENT_ID);
        controller.addClient(client);

        assertEquals(controller.filterClientByName(CLIENT_NAME).size(), 1);
    }

    @Test
    void filterRentalsByClientId() throws SQLException {
        Client client = new Client (CLIENT_NAME, CLIENT_BUSINESS);
        client.setId(CLIENT_ID);
        controller.addClient(client);

        WebDomain webDomain = new WebDomain(DOMAIN_NAME, DOMAIN_PRICE);
        webDomain.setId(DOMAIN_ID);
        controller.addDomain(webDomain);

        Rental rental = new Rental(CLIENT_ID, DOMAIN_ID, RENTAL_DATE, RENTAL_DURATION);
        rental.setId(RENTAL_ID);
        controller.addRental(rental);

        assertEquals(controller.filterRentalsByClientId(CLIENT_ID).size(), 1);
    }

    @Test
    void filterRentalsByDomainId() throws SQLException {
        Set<Rental> rentalsWithDomain = new HashSet<>();
        // rentals: none => none
        assertEquals(controller.filterRentalsByDomainId(DOMAIN_ID), rentalsWithDomain);

        Client client1 = new Client(CLIENT_NAME, CLIENT_BUSINESS);
        client1.setId(CLIENT_ID);
        controller.addClient(client1);

        Client client2 = new Client(CLIENT_NAME + " Andrei", CLIENT_BUSINESS);
        client2.setId(CLIENT_ID + 1);
        controller.addClient(client2);

        WebDomain webDomain1 = new WebDomain(DOMAIN_NAME, DOMAIN_PRICE);
        webDomain1.setId(DOMAIN_ID);
        controller.addDomain(webDomain1);

        WebDomain webDomain2 = new WebDomain(DOMAIN_NAME + "a", DOMAIN_PRICE);
        webDomain2.setId(DOMAIN_ID + 1);
        controller.addDomain(webDomain2);

        Rental rental1 = new Rental(client1.getId(), webDomain1.getId(), RENTAL_DATE, RENTAL_DURATION);
        rental1.setId(RENTAL_ID);
        controller.addRental(rental1);

        rentalsWithDomain.add(rental1);
        // rentals: r1(c1, d1) <d1> => r1(c1, d1)

        Rental rental2 = new Rental(client2.getId(), webDomain1.getId(), RENTAL_DATE, RENTAL_DURATION);
        rental2.setId(RENTAL_ID + 1);
        controller.addRental(rental2);

        rentalsWithDomain.add(rental2);
        // rentals: r1(c1, d1), r2(c2, d1) <d1> => r1(c1, d1), r2(c2, d1)
        assertEquals(controller.filterRentalsByDomainId(webDomain1.getId()), rentalsWithDomain);

        Rental rental3 = new Rental(client1.getId(), webDomain2.getId(), RENTAL_DATE, RENTAL_DURATION);
        rental3.setId(RENTAL_ID + 2);
        controller.addRental(rental3);

        // rentals: r1(c1, d1), r2(c2, d1), r3(c1, d2) <d1> => r1(c1, d1), r2(c2, d1)
        assertEquals(controller.filterRentalsByDomainId(webDomain1.getId()), rentalsWithDomain);
    }

    @Test
    void getMostCommonTld() throws SQLException {
        Client client = new Client (CLIENT_NAME, CLIENT_BUSINESS);
        client.setId(CLIENT_ID);
        controller.addClient(client);

        WebDomain webDomain = new WebDomain(DOMAIN_NAME, DOMAIN_PRICE);
        webDomain.setId(DOMAIN_ID);
        controller.addDomain(webDomain);

        Rental rental = new Rental(CLIENT_ID, DOMAIN_ID, RENTAL_DATE, RENTAL_DURATION);
        rental.setId(RENTAL_ID);
        controller.addRental(rental);

        WebDomain webDomain2 = new WebDomain(UPDATED_DOMAIN_NAME, UPDATED_DOMAIN_PRICE);
        webDomain2.setId(DOMAIN_ID_2);
        controller.addDomain(webDomain2);

        Rental rental2 = new Rental(CLIENT_ID, DOMAIN_ID_2, UPDATED_RENTAL_DATE, UPDATED_RENTAL_DURATION);
        rental2.setId(RENTAL_ID_2);
        controller.addRental(rental2);

        Set<String> result = new HashSet<>();
        result.add(DOMAIN_NAME.split("\\.")[DOMAIN_NAME.split("\\.").length - 1]);
        result.add(UPDATED_DOMAIN_NAME.split("\\.")[UPDATED_DOMAIN_NAME.split("\\.").length - 1]);

        assertEquals(controller.getMostCommonTld(), result);
    }
}
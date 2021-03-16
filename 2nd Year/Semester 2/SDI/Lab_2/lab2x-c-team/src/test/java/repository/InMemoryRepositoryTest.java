package repository;

import model.Client;
import model.Rental;
import model.WebDomain;
import model.validators.ClientValidator;
import model.validators.RentalValidator;
import model.validators.WebDomainValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryRepositoryTest {
    private static final Long CLIENT_ID = 1L;
    private static final Long DOMAIN_ID = 1L;
    private static final Long RENTAL_ID = 1L;

    private static final String CLIENT_NAME = "One Name";
    private static final Boolean CLIENT_BUSINESS = false;

    private static final String DOMAIN_NAME = "cteam.org";
    private static final Integer DOMAIN_PRICE = 100;

    private static final Integer RENTAL_DURATION = 15;
    private static final String RENTAL_DATE = "02-10-2020";

    private static Repository<Long, Client> clientRepository;
    private static Repository<Long, WebDomain> domainRepository;
    private static Repository<Long, Rental> rentalRepository;

    @BeforeEach
    void setUp() {
        clientRepository = new InMemoryRepository<>(new ClientValidator());
        domainRepository = new InMemoryRepository<>(new WebDomainValidator());
        rentalRepository = new InMemoryRepository<>(new RentalValidator());
    }

    @AfterEach
    void tearDown() {
        clientRepository = null;
        domainRepository = null;
        rentalRepository = null;
    }

    @Test
    void findOne() {
    }

    @Test
    void findAll() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() throws SQLException {
        Set<Client> clients = new HashSet<>();
        assertEquals(clientRepository.findAll(), clients);

        Client client = new Client(CLIENT_NAME, CLIENT_BUSINESS);
        client.setId(CLIENT_ID);
        clientRepository.save(client);

        Set<WebDomain> webDomains = new HashSet<>();
        assertEquals(domainRepository.findAll(), webDomains);

        WebDomain webDomain = new WebDomain(DOMAIN_NAME, DOMAIN_PRICE);
        webDomain.setId(DOMAIN_ID);
        domainRepository.save(webDomain);

        Set<Rental> rentals = new HashSet<>();
        assertEquals(rentalRepository.findAll(), rentals);

        Rental rental = new Rental(client.getId(), webDomain.getId(), RENTAL_DATE, RENTAL_DURATION);
        rental.setId(RENTAL_ID);
        rentalRepository.save(rental);

        rentalRepository.delete(rental.getId());
        assertEquals(rentalRepository.findAll(), rentals);

        domainRepository.delete(webDomain.getId());
        assertEquals(domainRepository.findAll(), webDomains);

        clientRepository.delete(client.getId());
        assertEquals(clientRepository.findAll(), clients);
    }

    @Test
    void update() {
    }
}
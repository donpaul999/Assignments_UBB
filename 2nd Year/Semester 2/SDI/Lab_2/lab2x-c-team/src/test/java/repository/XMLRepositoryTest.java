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

import static org.junit.jupiter.api.Assertions.*;

public class XMLRepositoryTest {
    private static final Client FIRST_CLIENT = new Client("Test One", false);
    private static final WebDomain FIRST_DOMAIN = new WebDomain("one.com", 432);
    private static final Rental FIRST_RENTAL = new Rental(1L, 1L, "20-12-2020", 12);

    private XMLRepository<Long, Client> clientFileRepository;
    private XMLRepository<Long, WebDomain> webDomainFileRepository;
    private XMLRepository<Long, Rental> rentalFileRepository;

    @BeforeEach
    void setUp() {
        FIRST_CLIENT.setId(1L);
        FIRST_DOMAIN.setId(1L);
        FIRST_RENTAL.setId(1L);
        clientFileRepository = new XMLRepository<>(new ClientValidator(), this.getClass().getResource("clients.xml").getPath(), Client.class);
        webDomainFileRepository = new XMLRepository<>(new WebDomainValidator(), this.getClass().getResource("webdomains.xml").getPath(), WebDomain.class);
        rentalFileRepository = new XMLRepository<>(new RentalValidator(), this.getClass().getResource("rentals.xml").getPath(), Rental.class);
    }

    @AfterEach
    void tearDown() {
        clientFileRepository = null;
        webDomainFileRepository = null;
        rentalFileRepository = null;
    }

    @Test
    void readFromFile() {
        Iterable<Client> clients = clientFileRepository.findAll();
        assertEquals(3, clients.spliterator().estimateSize());
        assertEquals(FIRST_CLIENT, clientFileRepository.findOne(1L).orElse(null));

        Iterable<WebDomain> domains = webDomainFileRepository.findAll();
        assertEquals(3, domains.spliterator().estimateSize());
        assertEquals(FIRST_DOMAIN, webDomainFileRepository.findOne(1L).orElse(null));

        Iterable<Rental> rentals = rentalFileRepository.findAll();
        assertEquals(2, rentals.spliterator().estimateSize());
        assertEquals(FIRST_RENTAL, rentalFileRepository.findOne(1L).orElse(null));
    }

    @Test
    void writeToFile(){
        clientFileRepository.writeToFile();
        Iterable<Client> clients = clientFileRepository.findAll();
        assertEquals(3, clients.spliterator().estimateSize());
        assertEquals(FIRST_CLIENT, clientFileRepository.findOne(1L).orElse(null));
    }
}

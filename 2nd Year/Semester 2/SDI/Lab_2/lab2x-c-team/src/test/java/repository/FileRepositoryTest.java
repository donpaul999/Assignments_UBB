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

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FileRepositoryTest {
    private static final Long CLIENT_ID = 1L;
    private static final Long DOMAIN_ID = 1L;
    private static final Long RENTAL_ID = 1L;

    private static final String CLIENT_NAME = "Ciupe Sergiu";
    private static final Boolean CLIENT_BUSINESS = false;

    private static final String DOMAIN_NAME = "cteam.org";
    private static final Integer DOMAIN_PRICE = 100;

    private static final Integer RENTAL_DURATION = 15;
    private static final String RENTAL_DATE = "02-10-2020";

    private FileRepository<Long, Client> clientFileRepository;
    private FileRepository<Long, WebDomain> webDomainFileRepository;
    private FileRepository<Long, Rental> rentalFileRepository;

    @BeforeEach
    void setUp() {
        clientFileRepository = new FileRepository<>(new ClientValidator(), "./src/test/java/resources/clients.txt", Client.class);
        webDomainFileRepository = new FileRepository<>(new WebDomainValidator(), "./src/test/java/resources/webdomains.txt", WebDomain.class);
        rentalFileRepository = new FileRepository<>(new RentalValidator(), "./src/test/java/resources/rentals.txt", Rental.class);
    }

    @AfterEach
    void tearDown() {
        clientFileRepository = null;
        webDomainFileRepository = null;
        rentalFileRepository = null;
    }

    @Test
    void loadFile_writeFile() {
        Client client1 = new Client(CLIENT_NAME, CLIENT_BUSINESS);
        client1.setId(CLIENT_ID);
        WebDomain webDomain1 = new WebDomain(DOMAIN_NAME, DOMAIN_PRICE);
        webDomain1.setId(DOMAIN_ID);
        Rental rental1 = new Rental(client1.getId(), webDomain1.getId(), RENTAL_DATE, RENTAL_DURATION);
        rental1.setId(RENTAL_ID);

        clientFileRepository.save(client1);
        webDomainFileRepository.save(webDomain1);
        rentalFileRepository.save(rental1);

        Set<Client> clients1 = clientFileRepository.loadFile();
        assertTrue(clients1.contains(client1));

        Set<WebDomain> webDomains1 = webDomainFileRepository.loadFile();
        assertTrue(webDomains1.contains(webDomain1));

        Set<Rental> rentals1 = rentalFileRepository.loadFile();
        assertTrue(rentals1.contains(rental1));

        Client client2 = new Client(CLIENT_NAME + " Calin", CLIENT_BUSINESS);
        client2.setId(CLIENT_ID + 1);
        WebDomain webDomain2 = new WebDomain(DOMAIN_NAME + "org", DOMAIN_PRICE + 100);
        webDomain2.setId(DOMAIN_ID + 1);
        Rental rental2 = new Rental(client2.getId(), webDomain2.getId(), RENTAL_DATE, RENTAL_DURATION + 5);
        rental2.setId(RENTAL_ID + 1);

        clientFileRepository.save(client2);
        webDomainFileRepository.save(webDomain2);
        rentalFileRepository.save(rental2);

        Set<Client> clients2 = clientFileRepository.loadFile();
        assertTrue(clients2.contains(client1));
        assertTrue(clients2.contains(client2));

        Set<WebDomain> webDomains2 = webDomainFileRepository.loadFile();
        assertTrue(webDomains2.contains(webDomain1));
        assertTrue(webDomains2.contains(webDomain2));

        Set<Rental> rentals2 = rentalFileRepository.loadFile();
        assertTrue(rentals2.contains(rental1));
        assertTrue(rentals2.contains(rental2));
    }
}
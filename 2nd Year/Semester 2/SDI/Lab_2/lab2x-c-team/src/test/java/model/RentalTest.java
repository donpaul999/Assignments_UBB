package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author ciupe
 */
public class RentalTest {

    private static final Long ID = 1L;
    private static final Long NEW_ID = 2L;
    private static final Integer DURATION = 10;
    private static final String DATE = "03-11-2019";

    public static final Long CLIENT_ID = 1L;
    public static final Long DOMAIN_ID = 1L;
    public static final Long NEW_CLIENT_ID = 2L;
    public static final Long NEW_DOMAIN_ID = 2L;

    private static final Client CLIENT = new Client("client1", true);
    private static final Client NEW_CLIENT = new Client ("client2", false);

    private static final WebDomain WEB_DOMAIN = new WebDomain("client1", 100);
    private static final WebDomain NEW_WEB_DOMAIN = new WebDomain("client2", 200);

    private static Rental rental;

    @BeforeEach
    public void setUp() {
        CLIENT.setId(CLIENT_ID);
        WEB_DOMAIN.setId(DOMAIN_ID);
        rental = new Rental(CLIENT.getId(), WEB_DOMAIN.getId(), DATE, DURATION);
        rental.setId(ID);
    }

    @AfterEach
    public void tearDown() throws Exception {
        rental=null;
    }

    @org.junit.jupiter.api.Test
    public void testGetClient(){
        assertEquals(CLIENT_ID, rental.getClientId());
    }

    @org.junit.jupiter.api.Test
    public void testGetDomain(){
        assertEquals(DOMAIN_ID, rental.getDomainId());
    }

    @org.junit.jupiter.api.Test
    public void testSetClient(){
        rental.setClientId(NEW_CLIENT_ID);
        assertEquals(NEW_CLIENT_ID, rental.getClientId());
    }

    @org.junit.jupiter.api.Test
    public void testSetDomain(){
        rental.setDomainId(NEW_DOMAIN_ID);
        assertEquals(NEW_DOMAIN_ID, rental.getDomainId());
    }

}

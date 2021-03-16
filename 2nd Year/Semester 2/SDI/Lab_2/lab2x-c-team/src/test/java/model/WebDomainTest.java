package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ciupe
 */
public class WebDomainTest {

    private static final String NAME = "client1";
    private static final String NEW_NAME = "client2";
    private static final Integer PRICE = 100;
    private static final Integer NEW_PRICE = 200;
    private static final Long ID = 1L;
    private static final Long NEW_ID = 2L;

    private static WebDomain webDomain;

    @BeforeEach
    public void setUp() {
        webDomain = new WebDomain(NAME, PRICE);
        webDomain.setId(ID);
    }

    @AfterEach
    public void tearDown() throws Exception {
        webDomain =null;
    }

    @org.junit.jupiter.api.Test
    void testGetName() {
        assertEquals(NAME, webDomain.getName());
    }

    @org.junit.jupiter.api.Test
    void testGetPrice() {
        assertEquals(PRICE, webDomain.getPrice());
    }

    @org.junit.jupiter.api.Test
    void testSetName() {
        webDomain.setName(NEW_NAME);
        assertEquals(NEW_NAME, webDomain.getName());
    }

    @org.junit.jupiter.api.Test
    void testSetPrice() {
        webDomain.setPrice(NEW_PRICE);
        assertEquals(NEW_PRICE, webDomain.getPrice());
    }

}

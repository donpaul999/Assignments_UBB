package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ciupe
 */
public class DomainTest {

    private static final String NAME = "client1";
    private static final String NEW_NAME = "client2";
    private static final Integer PRICE = 100;
    private static final Integer NEW_PRICE = 200;
    private static final Long ID = 1L;
    private static final Long NEW_ID = 2L;

    private static Domain domain;

    @BeforeEach
    public void setUp() {
        domain = new Domain(NAME, PRICE);
        domain.setId(ID);
    }

    @AfterEach
    public void tearDown() throws Exception {
        domain=null;
    }

    @org.junit.jupiter.api.Test
    void testGetName() {
        assertEquals(NAME, domain.getName());
    }

    @org.junit.jupiter.api.Test
    void testGetPrice() {
        assertEquals(PRICE, domain.getPrice());
    }

    @org.junit.jupiter.api.Test
    void testSetName() {
        domain.setName(NEW_NAME);
        assertEquals(NEW_NAME, domain.getName());
    }

    @org.junit.jupiter.api.Test
    void testSetPrice() {
        domain.setPrice(NEW_PRICE);
        assertEquals(NEW_PRICE, domain.getPrice());
    }

}

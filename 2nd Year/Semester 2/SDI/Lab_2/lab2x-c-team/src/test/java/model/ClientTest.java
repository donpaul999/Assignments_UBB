package model;

import model.Client;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    private static final Long ID = 1L;
    private static final Long NEW_ID = 2L;
    private static final String NAME = "client1";
    private static final String NEW_NAME = "client2";

    private static Client client;

    @BeforeEach
    public void setUp() {
        client = new Client(NAME, true);
        client.setId(ID);
    }

    @AfterEach
    public void tearDown() throws Exception {
        client=null;
    }

    @org.junit.jupiter.api.Test
    void testGetName() {
        assertEquals(NAME, client.getName());
    }

    @org.junit.jupiter.api.Test
    void testIsBusiness() {
        assertTrue(client.isBusiness());
    }

    @org.junit.jupiter.api.Test
    void testSetName() {
        client.setName(NEW_NAME);
        assertEquals(NEW_NAME, client.getName());
    }

    @org.junit.jupiter.api.Test
    void testSetIsBusiness() {
        client.setIsBusiness(false);
        assertFalse(client.isBusiness());
    }
}
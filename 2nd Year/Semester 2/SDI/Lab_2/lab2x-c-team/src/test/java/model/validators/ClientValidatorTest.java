package model.validators;

import model.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClientValidatorTest {
    private static final String validName1 = "Ciupe Sergiu";
    private static final String validName2 = "Ciupe Sergiu Calin";
    private static final String validName3 = "Xi Lu";
    private static final String invalidName1 = " Ciupe Sergiu";
    private static final String invalidName2 = "Ciupe Sergiu ";
    private static final String invalidName3 = "Ciupe  Sergiu";
    private static final String invalidName4 = "ciupe Sergiu";
    private static final String invalidName5 = "Ciupe sergiu";
    private static final String invalidName6 = "C1upe Sergiu";
    private static final String invalidName7 = "Ciupe $ergiu";

    private static final Long validId1 = 1L;
    private static final Long validId2 = 2L;
    private static final Long validId3 = 3L;
    private static final Long validId4 = 4L;
    private static final Long invalidId1 = 0L;
    private static final Long invalidId2 = -1L;

    private static Client validClient1;
    private static Client validClient2;
    private static Client validClient3;
    private static Client clientWithInvalidId1;
    private static Client clientWithInvalidId2;
    private static Client clientWithInvalidName1;
    private static Client clientWithInvalidName2;
    private static Client clientWithInvalidName3;
    private static Client clientWithInvalidName4;
    private static Client clientWithInvalidName5;
    private static Client clientWithInvalidName6;
    private static Client clientWithInvalidName7;

    private static ClientValidator clientValidator;

    @BeforeEach
    void setUp() {
        clientValidator = new ClientValidator();

        validClient1 = new Client(validName1, true);
        validClient1.setId(validId1);

        validClient2 = new Client(validName2, true);
        validClient2.setId(validId2);

        validClient3 = new Client(validName3, true);
        validClient3.setId(validId3);

        clientWithInvalidId1 = new Client(validName1, true);
        clientWithInvalidId1.setId(invalidId1);

        clientWithInvalidId2 = new Client(validName1, true);
        clientWithInvalidId2.setId(invalidId2);

        clientWithInvalidName1 = new Client(invalidName1, true);
        clientWithInvalidName1.setId(validId4);

        clientWithInvalidName2 = new Client(invalidName2, true);
        clientWithInvalidName2.setId(validId4);

        clientWithInvalidName3 = new Client(invalidName3, true);
        clientWithInvalidName3.setId(validId4);

        clientWithInvalidName4 = new Client(invalidName4, true);
        clientWithInvalidName4.setId(validId4);

        clientWithInvalidName5 = new Client(invalidName5, true);
        clientWithInvalidName5.setId(validId4);

        clientWithInvalidName6 = new Client(invalidName6, true);
        clientWithInvalidName6.setId(validId4);

        clientWithInvalidName7 = new Client(invalidName7, true);
        clientWithInvalidName7.setId(validId4);
    }

    @AfterEach
    void tearDown() {
        clientValidator = null;

        validClient1 = null;
        validClient2 = null;
        validClient3 = null;

        clientWithInvalidId1 = null;
        clientWithInvalidId2 = null;
        clientWithInvalidName1 = null;
        clientWithInvalidName2 = null;
        clientWithInvalidName3 = null;
        clientWithInvalidName4 = null;
        clientWithInvalidName5 = null;
        clientWithInvalidName6 = null;
        clientWithInvalidName7 = null;
    }

    @Test
    void validate() {
        assertDoesNotThrow(() -> clientValidator.validate(validClient1));
        assertDoesNotThrow(() -> clientValidator.validate(validClient2));
        assertDoesNotThrow(() -> clientValidator.validate(validClient3));

        assertThrows(ValidatorException.class, () -> clientValidator.validate(clientWithInvalidId1));
        assertThrows(ValidatorException.class, () -> clientValidator.validate(clientWithInvalidId2));

        assertThrows(ValidatorException.class, () -> clientValidator.validate(clientWithInvalidName1));
        assertThrows(ValidatorException.class, () -> clientValidator.validate(clientWithInvalidName2));
        assertThrows(ValidatorException.class, () -> clientValidator.validate(clientWithInvalidName3));
        assertThrows(ValidatorException.class, () -> clientValidator.validate(clientWithInvalidName4));
        assertThrows(ValidatorException.class, () -> clientValidator.validate(clientWithInvalidName5));
        assertThrows(ValidatorException.class, () -> clientValidator.validate(clientWithInvalidName6));
        assertThrows(ValidatorException.class, () -> clientValidator.validate(clientWithInvalidName7));
    }
}
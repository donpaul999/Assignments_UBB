package model.validators;

import model.Client;
import model.Rental;
import model.WebDomain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RentalValidatorTest {
    private static final Long validRentalId1 = 1L;
    private static final Long validRentalId2 = 2L;
    private static final Long validRentalId3 = 3L;
    private static final Long invalidRentalId1 = 0L;

    private static final Long validClientId1 = 1L;
    private static final Long validClientId2 = 2L;
    private static final Long invalidClientId = 0L;
    private static final String validClientName = "Ciupe Sergiu";

    private static final Long validDomainId1 = 1L;
    private static final Long validDomainId2 = 2L;
    private static final Long invalidDomainId = 0L;
    private static final String validDomainName = "cteam.com";
    private static final Integer validDomainPrice = 1;

    private static final Integer validDuration = 100;

    private static final String validStartDate = "02-02-2012";
    private static final String invalidStartDate1 = "02.02.2012";
    private static final String invalidStartDate2 = "02 02 2012";
    private static final String invalidStartDate3 = "02-13-2012";
    private static final String invalidStartDate4 = "32-10-2012";

    private static Client validClient1;
    private static Client validClient2;

    private static WebDomain validDomain1;
    private static WebDomain validDomain2;

    private static Rental validRental1;
    private static Rental validRental2;
    private static Rental rentalWithInvalidClientId;
    private static Rental rentalWithInvalidDomainId;
    private static Rental rentalWithInvalidId;
    private static Rental rentalWithInvalidStartDate1;
    private static Rental rentalWithInvalidStartDate2;
    private static Rental rentalWithInvalidStartDate3;
    private static Rental rentalWithInvalidStartDate4;

    private static RentalValidator rentalValidator;

    @BeforeEach
    void setUp() {
        rentalValidator = new RentalValidator();

        validClient1 = new Client(validClientName, true);
        validClient1.setId(validClientId1);
        validClient2 = new Client(validClientName, true);
        validClient2.setId(validClientId2);

        validDomain1 = new WebDomain(validDomainName, validDomainPrice);
        validDomain1.setId(validDomainId1);
        validDomain2 = new WebDomain(validDomainName, validDomainPrice);
        validDomain2.setId(validDomainId2);

        validRental1 = new Rental(validClient1.getId(), validDomain1.getId(), validStartDate, validDuration);
        validRental1.setId(validRentalId1);
        validRental2 = new Rental(validClient2.getId(), validDomain2.getId(), validStartDate, validDuration);
        validRental2.setId(validRentalId2);

        rentalWithInvalidClientId = new Rental(invalidClientId, validDomain1.getId(), validStartDate, validDuration);
        rentalWithInvalidClientId.setId(validRentalId3);

        rentalWithInvalidDomainId = new Rental(validClient1.getId(), invalidDomainId, validStartDate, validDuration);
        rentalWithInvalidDomainId.setId(validRentalId3);

        rentalWithInvalidId = new Rental(validClient1.getId(), validDomain1.getId(), validStartDate, validDuration);
        rentalWithInvalidId.setId(invalidRentalId1);

        rentalWithInvalidStartDate1 = new Rental(validClient1.getId(), validDomain1.getId(), invalidStartDate1, validDuration);
        rentalWithInvalidStartDate1.setId(validRentalId3);

        rentalWithInvalidStartDate2 = new Rental(validClient1.getId(), validDomain1.getId(), invalidStartDate2, validDuration);
        rentalWithInvalidStartDate2.setId(validRentalId3);

        rentalWithInvalidStartDate3 = new Rental(validClient1.getId(), validDomain1.getId(), invalidStartDate3, validDuration);
        rentalWithInvalidStartDate3.setId(validRentalId3);

        rentalWithInvalidStartDate4 = new Rental(validClient1.getId(), validDomain1.getId(), invalidStartDate4, validDuration);
        rentalWithInvalidStartDate4.setId(validRentalId3);
    }

    @AfterEach
    void tearDown() {
        rentalValidator = null;

        validClient1 = null;
        validClient2 = null;

        validDomain1 = null;
        validDomain2 = null;

        validRental1 = null;
        validRental2 = null;

        rentalWithInvalidClientId = null;
        rentalWithInvalidDomainId = null;
        rentalWithInvalidId = null;
        rentalWithInvalidStartDate1 = null;
        rentalWithInvalidStartDate2 = null;
        rentalWithInvalidStartDate3 = null;
        rentalWithInvalidStartDate4 = null;
    }

    @Test
    void validate() {
        assertDoesNotThrow(() -> rentalValidator.validate(validRental1));
        assertDoesNotThrow(() -> rentalValidator.validate(validRental2));

        assertThrows(ValidatorException.class, () -> rentalValidator.validate(rentalWithInvalidClientId));
        assertThrows(ValidatorException.class, () -> rentalValidator.validate(rentalWithInvalidDomainId));
        assertThrows(ValidatorException.class, () -> rentalValidator.validate(rentalWithInvalidId));
        assertThrows(ValidatorException.class, () -> rentalValidator.validate(rentalWithInvalidStartDate1));
        assertThrows(ValidatorException.class, () -> rentalValidator.validate(rentalWithInvalidStartDate2));
        assertThrows(ValidatorException.class, () -> rentalValidator.validate(rentalWithInvalidStartDate3));
        assertThrows(ValidatorException.class, () -> rentalValidator.validate(rentalWithInvalidStartDate4));
    }
}
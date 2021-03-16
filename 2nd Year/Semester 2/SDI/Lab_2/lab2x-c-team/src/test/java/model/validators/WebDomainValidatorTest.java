package model.validators;

import model.WebDomain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WebDomainValidatorTest {
    private static final String validName1 = "x.y";
    private static final String validName2 = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy";
    private static final String validName3 = "9931-8-73-6-af-aub2-78--4-17.c-o-m";
    private static final String validName4 = "99---31-8-73-6-af-aub2-78--4-17.c-o-m";
    private static final String invalidName1 = "x.y.";
    private static final String invalidName2 = "x..y";
    private static final String invalidName3 = ".x.y";
    private static final String invalidName4 = "ab.c.com";
    private static final String invalidName5 = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy";
    private static final String invalidName6 = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy";
    private static final String invalidName7 = "-x.com";
    private static final String invalidName8 = "x-.com";
    private static final String invalidName9 = "x.com-";
    private static final String invalidName10 = "x.-com";

    private static final Long validId1 = 1L;
    private static final Long validId2 = 2L;
    private static final Long validId3 = 3L;
    private static final Long validId4 = 4L;
    private static final Long invalidId1 = 0L;
    private static final Long invalidId2 = -1L;

    private static final Integer validPrice = 1;
    private static final Integer invalidPrice1 = 0;
    private static final Integer invalidPrice2 = -1;

    private static WebDomain validDomain1;
    private static WebDomain validDomain2;
    private static WebDomain validDomain3;
    private static WebDomain domainWithInvalidId1;
    private static WebDomain domainWithInvalidId2;
    private static WebDomain domainWithInvalidPrice1;
    private static WebDomain domainWithInvalidPrice2;
    private static WebDomain domainWithInvalidName1;
    private static WebDomain domainWithInvalidName2;
    private static WebDomain domainWithInvalidName3;
    private static WebDomain domainWithInvalidName4;
    private static WebDomain domainWithInvalidName5;
    private static WebDomain domainWithInvalidName6;
    private static WebDomain domainWithInvalidName7;
    private static WebDomain domainWithInvalidName8;
    private static WebDomain domainWithInvalidName9;
    private static WebDomain domainWithInvalidName10;

    private static WebDomainValidator webDomainValidator;

    @BeforeEach
    void setUp() {
        webDomainValidator = new WebDomainValidator();

        validDomain1 = new WebDomain(validName1, validPrice);
        validDomain1.setId(validId1);

        validDomain2 = new WebDomain(validName2, validPrice);
        validDomain2.setId(validId2);

        validDomain3 = new WebDomain(validName3, validPrice);
        validDomain3.setId(validId3);

        domainWithInvalidId1 = new WebDomain(validName4, validPrice);
        domainWithInvalidId1.setId(invalidId1);

        domainWithInvalidId2 = new WebDomain(validName4, validPrice);
        domainWithInvalidId2.setId(invalidId2);

        domainWithInvalidPrice1 = new WebDomain(validName4, invalidPrice1);
        domainWithInvalidPrice1.setId(validId4);

        domainWithInvalidPrice2 = new WebDomain(validName4, invalidPrice2);
        domainWithInvalidPrice2.setId(validId4);

        domainWithInvalidName1 = new WebDomain(invalidName1, validPrice);
        domainWithInvalidName1.setId(validId4);

        domainWithInvalidName2 = new WebDomain(invalidName2, validPrice);
        domainWithInvalidName2.setId(validId4);

        domainWithInvalidName3 = new WebDomain(invalidName3, validPrice);
        domainWithInvalidName3.setId(validId4);

        domainWithInvalidName4 = new WebDomain(invalidName4, validPrice);
        domainWithInvalidName4.setId(validId4);

        domainWithInvalidName5 = new WebDomain(invalidName5, validPrice);
        domainWithInvalidName5.setId(validId4);

        domainWithInvalidName6 = new WebDomain(invalidName6, validPrice);
        domainWithInvalidName6.setId(validId4);

        domainWithInvalidName7 = new WebDomain(invalidName7, validPrice);
        domainWithInvalidName7.setId(validId4);

        domainWithInvalidName8 = new WebDomain(invalidName8, validPrice);
        domainWithInvalidName8.setId(validId4);

        domainWithInvalidName9 = new WebDomain(invalidName9, validPrice);
        domainWithInvalidName9.setId(validId4);

        domainWithInvalidName10 = new WebDomain(invalidName10, validPrice);
        domainWithInvalidName10.setId(validId4);
    }

    @AfterEach
    void tearDown() {
        webDomainValidator = null;

        validDomain1 = null;
        validDomain2 = null;
        validDomain3 = null;

        domainWithInvalidId1 = null;
        domainWithInvalidId2 = null;
        domainWithInvalidPrice1 = null;
        domainWithInvalidPrice2 = null;
        domainWithInvalidName1 = null;
        domainWithInvalidName2 = null;
        domainWithInvalidName3 = null;
        domainWithInvalidName4 = null;
        domainWithInvalidName5 = null;
        domainWithInvalidName6 = null;
        domainWithInvalidName7 = null;
        domainWithInvalidName8 = null;
        domainWithInvalidName9 = null;
        domainWithInvalidName10 = null;
    }

    @Test
    void validate() {
        assertDoesNotThrow(() -> webDomainValidator.validate(validDomain1));
        assertDoesNotThrow(() -> webDomainValidator.validate(validDomain2));
        assertDoesNotThrow(() -> webDomainValidator.validate(validDomain3));

        assertThrows(ValidatorException.class, () -> webDomainValidator.validate(domainWithInvalidId1));
        assertThrows(ValidatorException.class, () -> webDomainValidator.validate(domainWithInvalidId2));

        assertThrows(ValidatorException.class, () -> webDomainValidator.validate(domainWithInvalidPrice1));
        assertThrows(ValidatorException.class, () -> webDomainValidator.validate(domainWithInvalidPrice2));

        assertThrows(ValidatorException.class, () -> webDomainValidator.validate(domainWithInvalidName1));
        assertThrows(ValidatorException.class, () -> webDomainValidator.validate(domainWithInvalidName2));
        assertThrows(ValidatorException.class, () -> webDomainValidator.validate(domainWithInvalidName3));
        assertThrows(ValidatorException.class, () -> webDomainValidator.validate(domainWithInvalidName4));
        assertThrows(ValidatorException.class, () -> webDomainValidator.validate(domainWithInvalidName5));
        assertThrows(ValidatorException.class, () -> webDomainValidator.validate(domainWithInvalidName6));
        assertThrows(ValidatorException.class, () -> webDomainValidator.validate(domainWithInvalidName7));
        assertThrows(ValidatorException.class, () -> webDomainValidator.validate(domainWithInvalidName8));
        assertThrows(ValidatorException.class, () -> webDomainValidator.validate(domainWithInvalidName9));
        assertThrows(ValidatorException.class, () -> webDomainValidator.validate(domainWithInvalidName10));
    }
}
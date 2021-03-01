package model.validators;

import model.Domain;

import java.util.Optional;

public class DomainValidator implements Validator<Domain> {
    /**
     * Provides the validation of a domain entity.
     *
     * @param entity the domain entity.
     * @throws ValidatorException if entity's id or price is not greater than 0 or the entity's name
     * doesn't respect the format below:
     * <p> Characters should only be a-z | A-Z | 0-9 and period (.) and dash (-). </p>
     * <p> There can be only 1 period (.). </p>
     * <p> The domain name part should not start or end with dash (-) (e.g. -google-.com). </p>
     * <p> The domain name part should be between 1 and 63 characters long. </p>
     */
    @Override
    public void validate(Domain entity) throws ValidatorException {
        Optional.of(entity)
                .filter(domain -> domain.getName().matches("^[a-zA-Z0-9]([a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?\\.[a-zA-Z0-9]([a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?$"))
                .orElseThrow(() -> new ValidatorException(String.format("Domain with id %d has a name that doesn't respect the format of a domain name: %s", entity.getId(), entity.getName())));

        Optional.of(entity)
                .filter(domain -> domain.getId() > 0)
                .orElseThrow(() -> new ValidatorException(String.format("Domain's id %d is not greater than 0.", entity.getId())));

        Optional.of(entity)
                .filter(domain -> domain.getPrice() > 0)
                .orElseThrow(() -> new ValidatorException(String.format("Domain with id %d has a price that is not greater than 0: %d.", entity.getId(), entity.getPrice())));
    }
}

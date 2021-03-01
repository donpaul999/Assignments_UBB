package model.validators;

import model.Client;

import java.util.Optional;

public class ClientValidator implements Validator<Client>  {
    /**
     * This function validates a client entity.
     *
     * @param entity the supposed-to-be client.
     * @throws ValidatorException if entity's id is not greater than 0 or the entity's name contains non-alphabetic characters.
     */
    @Override
    public void validate(Client entity) throws ValidatorException {
        Optional.of(entity)
                .filter(client -> client.getId() > 0)
                .orElseThrow(() -> new ValidatorException(String.format("Client's id %d is not greater than 0.", entity.getId())));

        Optional.of(entity)
                .filter(client -> client.getName().matches("^[a-zA-Z]+$"))
                .orElseThrow(() -> new ValidatorException(String.format("Client with id %d has a name that contains non-alphabetic character(s): %s.", entity.getId(), entity.getName())));
    }
}

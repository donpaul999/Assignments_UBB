package model.validators;

import model.Client;

import java.util.Optional;

public class ClientValidator implements Validator<Client>  {
    /**
     * This function validates a client entity.
     *
     * @param entity the supposed-to-be client.
     * @throws ValidatorException if entity's id is not greater than 0 or the entity's name
     * doesn't respect the format below:
     * <p> Characters should only be a-z | A-Z | and empty space ( ). </p>
     * <p> Full name can't start or end with empty space. </p>
     * <p> Between words there has to be exactly one empty space. </p>
     */
    @Override
    public void validate(Client entity) throws ValidatorException {
        Optional.of(entity)
                .filter(client -> client.getId() > 0)
                .orElseThrow(() -> new ValidatorException(String.format("Client's id %d is not greater than 0.", entity.getId())));

        Optional.of(entity)
                .filter(client -> client.getName().matches("^([A-Z][a-z]+\\s)+[A-Z][a-zA-Z]+$"))
                .orElseThrow(() -> new ValidatorException(String.format("Client with id %d has a name that doesn't respect the format of a client name: %s.", entity.getId(), entity.getName())));
    }
}

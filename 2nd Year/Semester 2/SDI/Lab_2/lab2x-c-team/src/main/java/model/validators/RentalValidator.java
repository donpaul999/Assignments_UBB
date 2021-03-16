package model.validators;

import model.Rental;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

public class RentalValidator implements Validator<Rental> {
    /**
     * This function validates a rental entity.
     *
     * @param entity the supposed-to-be rental.
     * @throws ValidatorException if entity's duration is not greater than 0 or the entity's starting date is invalid.
     */
    @Override
    public void validate(Rental entity) throws ValidatorException {
        Optional.of(entity)
                .filter(rental -> rental.getId() > 0)
                .orElseThrow(() -> new ValidatorException(String.format("Rental's id %d is not greater than 0.", entity.getClientId())));

        Optional.of(entity)
                .filter(rental -> rental.getClientId() > 0)
                .orElseThrow(() -> new ValidatorException(String.format("Client's id %d is not greater than 0.", entity.getClientId())));

        Optional.of(entity)
                .filter(rental -> rental.getDomainId() > 0)
                .orElseThrow(() -> new ValidatorException(String.format("Domain's id %d is not greater than 0.", entity.getDomainId())));

        Optional.of(entity)
                .filter(rental -> rental.getDuration() > 0)
                .orElseThrow(() -> new ValidatorException(String.format("Rental with id %d has a duration that is not greater than 0: %d.", entity.getId(), entity.getDuration())));

        Optional.of(entity)
                .filter(rental -> dateIsValid(rental.getStartDate()))
                .orElseThrow(() -> new ValidatorException(String.format("Rental with id %d has an invalid start date: %s.", entity.getId(), entity.getStartDate().toString())));
    }

    /**
     * This function validates a date in the format specified by the pattern "dd-MM-yyyy" (day-month-year).
     *
     * @param date date to validate
     * @return true is the date is valid, false otherwise
     * @see SimpleDateFormat
     */
    private boolean dateIsValid(String date) {
        DateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        simpleDateFormat.setLenient(false);
        try {
            simpleDateFormat.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}

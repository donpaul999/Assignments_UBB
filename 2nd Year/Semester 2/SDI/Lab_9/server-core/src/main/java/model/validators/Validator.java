package model.validators;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}

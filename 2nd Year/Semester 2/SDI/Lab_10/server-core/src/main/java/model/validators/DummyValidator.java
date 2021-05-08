package model.validators;

public class DummyValidator<T> implements Validator<T>{
    @Override
    public void validate(T entity) throws ValidatorException { }
}

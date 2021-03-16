package repository.database;

import model.BaseEntity;
import model.validators.Validator;
import model.validators.ValidatorException;
import repository.Repository;

import java.util.Optional;

public abstract class DatabaseRepository<ID, T extends BaseEntity<ID>> implements Repository<ID, T> {
    protected final Validator<T> validator;
    protected final String url;
    protected final String user;
    protected final String password;

    public DatabaseRepository(Validator<T> validator, String url, String user, String password) {
        this.validator = validator;
        this.url = url;
        this.user = user;
        this.password = password;
    }
}

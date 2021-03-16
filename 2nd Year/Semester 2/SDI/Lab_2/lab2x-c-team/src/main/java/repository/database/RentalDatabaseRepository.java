package repository.database;

import exceptions.RepositoryException;
import model.Client;
import model.Rental;
import model.validators.RentalValidator;
import model.validators.ValidatorException;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class RentalDatabaseRepository extends DatabaseRepository<Long, Rental> {
    public RentalDatabaseRepository(RentalValidator validator, String url, String name, String password) {
        super(validator, url, name, password);
    }

    @Override
    public Optional<Rental> findOne(Long aLong) {
        String sql = "select * from \"WebDomainRental\".\"Rental\" where id = " + aLong;

        try (var connection = DriverManager.getConnection(url, user, password);
             var preparedStatement = connection.prepareStatement(sql);
             var resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return Optional.of(this.getRentalFromResultSet(resultSet));
            }
        } catch (Exception e) {
            throw new RepositoryException(String.format("Error while reading from database: %s", e.getMessage()));
        }

        return Optional.empty();
    }

    @Override
    public Iterable<Rental> findAll() {
        String sql = "select * from \"WebDomainRental\".\"Rental\"";

        try (var connection = DriverManager.getConnection(url, user, password);
             var preparedStatement = connection.prepareStatement(sql);
             var resultSet = preparedStatement.executeQuery()) {
            Set<Rental> rentals = new HashSet<>();
            while (resultSet.next()) {
                rentals.add(this.getRentalFromResultSet(resultSet));
            }
            return rentals;
        } catch (Exception e) {
            throw new RepositoryException(String.format("Error while reading from database: %s", e.getMessage()));
        }
    }

    private Rental getRentalFromResultSet(ResultSet resultSet) throws SQLException {
        Long rentalId = (long) resultSet.getInt("id");
        Long clientId = (long) resultSet.getInt("clientId");
        Long webDomainId = (long) resultSet.getInt("webDomainId");
        String rentalStartDate = resultSet.getString("startDate");
        Integer rentalDuration = resultSet.getInt("duration");

        Rental rental = new Rental(clientId, webDomainId, rentalStartDate, rentalDuration);
        rental.setId(rentalId);

        return rental;
    }

    @Override
    public Optional<Rental> save(Rental entity) throws ValidatorException {
        validator.validate(entity);

        Optional<Rental> rental = null;
        rental = this.findOne(entity.getId());

        String entityAttributes = entity.getId() + "," +
                entity.getClientId() + "," +
                entity.getDomainId() + "," +
                "'" + entity.getStartDate() + "'" + "," +
                (entity.getDuration());
        String sql = "insert into \"WebDomainRental\".\"Rental\" (\"id\", \"clientId\", \"webDomainId\", \"startDate\", \"duration\") " +
                "values(" + entityAttributes + ")";

        if(rental.isEmpty()) {
            try (var connection = DriverManager.getConnection(url, user, password);
                 var preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                throw new RepositoryException(String.format("Error while writing to database: %s", e.getMessage()));
            }
        }
        return rental;
    }

    @Override
    public Optional<Rental> delete(Long aLong) {
        Rental rental = this.findOne(aLong).orElse(null);

        String sql = "delete from \"WebDomainRental\".\"Rental\" where id =?";
        try (var connection = DriverManager.getConnection(this.url, this.user, this.password);
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, aLong);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException(String.format("Error while deleting from database: %s", e.getMessage()));
        }

        return Optional.ofNullable(rental);
    }

    @Override
    public Optional<Rental> update(Rental entity) throws ValidatorException {

        String sql = "update \"WebDomainRental\".\"Rental\" set \"clientId\"=?, \"webDomainId\"=?, \"startDate\"=?, duration=? where id=?";
        try (var connection = DriverManager.getConnection(url, user, password);
             var preparedStatement = connection.prepareStatement(sql)) {
            Optional<Rental> oldRental = findOne(entity.getId());

            preparedStatement.setLong(1, entity.getClientId());
            preparedStatement.setLong(2, entity.getDomainId());
            preparedStatement.setString(3, entity.getStartDate());
            preparedStatement.setInt(4, entity.getDuration());
            preparedStatement.setLong(5, entity.getId());
            preparedStatement.executeUpdate();

            return oldRental;
        } catch (Exception e) {
            throw new RepositoryException(String.format("Error while updating database: %s", e.getMessage()));
        }
    }
}

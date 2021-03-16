package repository.database;

import exceptions.RepositoryException;
import model.Client;
import model.validators.ClientValidator;
import model.validators.ValidatorException;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ClientDatabaseRepository extends DatabaseRepository<Long, Client> {
    public ClientDatabaseRepository(ClientValidator validator, String url, String name, String password) {
        super(validator, url, name, password);
    }

    @Override
    public Optional<Client> findOne(Long aLong) {
        String sql = "select * from \"WebDomainRental\".\"Client\" where id = " + aLong;

        try (var connection = DriverManager.getConnection(url, user, password);
             var preparedStatement = connection.prepareStatement(sql);
             var resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return Optional.of(this.getClientFromResultSet(resultSet));
            }
        } catch (Exception e) {
            throw new RepositoryException(String.format("Error while reading from database: %s", e.getMessage()));
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Client> findAll() {
        String sql = "select * from \"WebDomainRental\".\"Client\"";

        try (var connection = DriverManager.getConnection(url, user, password);
             var preparedStatement = connection.prepareStatement(sql);
             var resultSet = preparedStatement.executeQuery()) {
            Set<Client> clients = new HashSet<>();
            while (resultSet.next()) {
                clients.add(this.getClientFromResultSet(resultSet));
            }
            return clients;
        } catch (Exception e) {
            throw new RepositoryException(String.format("Error while reading from database: %s", e.getMessage()));
        }
    }

    private Client getClientFromResultSet(ResultSet resultSet) throws SQLException {
        Long clientId = (long) resultSet.getInt("id");
        String clientName = resultSet.getString("name");
        boolean isBusiness = resultSet.getBoolean("isBusiness");

        Client client = new Client(clientName, isBusiness);
        client.setId(clientId);

        return client;
    }

    @Override
    public Optional<Client> save(Client entity) throws ValidatorException {
        validator.validate(entity);

        var client = this.findOne(entity.getId());
        String entityAttributes = entity.getId() + "," +
                "'" + entity.getName() + "'" + "," +
                (entity.isBusiness());
        String sql = "insert into \"WebDomainRental\".\"Client\" (id, name, \"isBusiness\") values(" + entityAttributes + ")";

        if (client.isEmpty()) {
            try (var connection = DriverManager.getConnection(url, user, password);
                 var preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                throw new RepositoryException(String.format("Error while writing to database: %s", e.getMessage()));
            }
        }
        return client;
    }

    @Override
    public Optional<Client> delete(Long aLong) {
        Client client = this.findOne(aLong).orElse(null);

        String sql = "delete from \"WebDomainRental\".\"Client\" where id =?";
        try (var connection = DriverManager.getConnection(this.url, this.user, this.password);
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, aLong);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException(String.format("Error while deleting from database: %s", e.getMessage()));
        }

        return Optional.ofNullable(client);
    }

    @Override
    public Optional<Client> update(Client entity) throws ValidatorException {

        String sql = "update \"WebDomainRental\".\"Client\" set name=?, \"isBusiness\"=? where id=?";
        try (var connection = DriverManager.getConnection(url, user, password);
             var preparedStatement = connection.prepareStatement(sql)) {
            Optional<Client> oldClient = findOne(entity.getId());

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setBoolean(2, entity.isBusiness());
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.executeUpdate();

            return oldClient;
        } catch (Exception e) {
            throw new RepositoryException(String.format("Error while updating database: %s", e.getMessage()));
        }
    }
}

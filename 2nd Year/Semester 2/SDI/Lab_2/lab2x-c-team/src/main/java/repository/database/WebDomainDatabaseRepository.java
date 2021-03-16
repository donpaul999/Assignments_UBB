package repository.database;

import exceptions.RepositoryException;
import model.Client;
import model.WebDomain;
import model.validators.ValidatorException;
import model.validators.WebDomainValidator;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class WebDomainDatabaseRepository extends DatabaseRepository<Long, WebDomain> {
    public WebDomainDatabaseRepository(WebDomainValidator validator, String url, String name, String password) {
        super(validator, url, name, password);
    }

    @Override
    public Optional<WebDomain> findOne(Long aLong) {
        String sql = "select * from \"WebDomainRental\".\"WebDomain\" where id = " + aLong;

        try (var connection = DriverManager.getConnection(url, user, password);
             var preparedStatement = connection.prepareStatement(sql);
             var resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return Optional.of(this.getWebDomainFromResultSet(resultSet));
            }
        } catch (Exception e) {
            throw new RepositoryException(String.format("Error while reading from database: %s", e.getMessage()));
        }
        return Optional.empty();
    }

    @Override
    public Iterable<WebDomain> findAll() {
        String sql = "select * from \"WebDomainRental\".\"WebDomain\"";

        try (var connection = DriverManager.getConnection(url, user, password);
             var preparedStatement = connection.prepareStatement(sql);
             var resultSet = preparedStatement.executeQuery()) {
            Set<WebDomain> webDomains = new HashSet<>();
            while (resultSet.next()) {
                webDomains.add(this.getWebDomainFromResultSet(resultSet));
            }
            return webDomains;
        } catch (Exception e) {
            throw new RepositoryException(String.format("Error while reading from database: %s", e.getMessage()));
        }
    }

    private WebDomain getWebDomainFromResultSet(ResultSet resultSet) throws SQLException {
        Long webDomainId = (long) resultSet.getInt("id");
        String webDomainName = resultSet.getString("name");
        Integer webDomainPrice = resultSet.getInt("price");

        WebDomain webDomain = new WebDomain(webDomainName, webDomainPrice);
        webDomain.setId(webDomainId);

        return webDomain;
    }

    @Override
    public Optional<WebDomain> save(WebDomain entity) throws ValidatorException {
        validator.validate(entity);

        var webDomain = this.findOne(entity.getId());
        String entityAttributes = entity.getId() + "," +
                "'" + entity.getName() + "'" + "," +
                (entity.getPrice());
        String sql = "insert into \"WebDomainRental\".\"WebDomain\" (id, name, price) values(" + entityAttributes + ")";

        if (webDomain.isEmpty()) {
            try (var connection = DriverManager.getConnection(url, user, password);
                 var preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                throw new RepositoryException(String.format("Error while writing to database: %s", e.getMessage()));
            }
        }
        return webDomain;
    }

    @Override
    public Optional<WebDomain> delete(Long aLong) {
        WebDomain webDomain = this.findOne(aLong).orElse(null);

        String sql = "delete from \"WebDomainRental\".\"WebDomain\" where id =?";
        try (var connection = DriverManager.getConnection(this.url, this.user, this.password);
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, aLong);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException(String.format("Error while deleting from database: %s", e.getMessage()));
        }

        return Optional.ofNullable(webDomain);
    }

    @Override
    public Optional<WebDomain> update(WebDomain entity) throws ValidatorException {
        String sql = "update \"WebDomainRental\".\"WebDomain\" set name=?, price=? where id=?";
        try (var connection = DriverManager.getConnection(url, user, password);
             var preparedStatement = connection.prepareStatement(sql)) {
            Optional<WebDomain> oldWebDomain = findOne(entity.getId());

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getPrice());
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.executeUpdate();

            return oldWebDomain;
        } catch (Exception e) {
            throw new RepositoryException(String.format("Error while updating database: %s", e.getMessage()));
        }
    }
}

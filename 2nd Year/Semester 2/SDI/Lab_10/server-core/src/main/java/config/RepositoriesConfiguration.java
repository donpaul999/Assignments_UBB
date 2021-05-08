package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcOperations;

@Configuration
@Import(JdbcConfiguration.class)
public class RepositoriesConfiguration {
    @Autowired
    private JdbcOperations jdbcOperations;
}

package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RepositoriesConfiguration.class})
@ComponentScan({"services", "repository.database"})
public class ServerConfig {
}

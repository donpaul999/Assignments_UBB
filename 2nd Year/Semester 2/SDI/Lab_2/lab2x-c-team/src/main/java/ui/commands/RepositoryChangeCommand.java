package ui.commands;

import controller.Controller;
import exceptions.CommandException;
import model.Client;
import model.Rental;
import model.WebDomain;
import model.validators.ClientValidator;
import model.validators.RentalValidator;
import model.validators.WebDomainValidator;
import repository.FileRepository;
import repository.InMemoryRepository;
import repository.XMLRepository;
import repository.database.ClientDatabaseRepository;
import repository.database.RentalDatabaseRepository;
import repository.database.WebDomainDatabaseRepository;
import ui.ApplicationContext;
import ui.annotations.Command;

import java.sql.SQLException;
import java.util.Deque;
import java.util.Optional;

@Command(
        key = "repo",
        description = "Change the repository type.",
        usage = "repo <type(mem/file/xml/db)>"
)
public class RepositoryChangeCommand extends BaseCommand {
    private ApplicationContext context;
    private Controller controller;

    public RepositoryChangeCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void init(ApplicationContext context) {
        this.context = context;
        this.controller = context.getService(Controller.class).orElse(null);
    }

    @Override
    public void execute(Deque<String> args) throws SQLException {
        Optional.of(args.size())
                .filter(s -> s == 1)
                .orElseThrow(() -> new CommandException(String.format("Invalid number of parameters. Expected %d found %d", 1, args.size())));

        String repositoryType = args.pop();

        switch (repositoryType) {
            case "mem": {
                this.context.setShared("repository.client", new InMemoryRepository<>(new ClientValidator()));
                this.context.setShared("repository.webdomain", new InMemoryRepository<>(new WebDomainValidator()));
                this.context.setShared("repository.rental", new InMemoryRepository<>(new RentalValidator()));
                break;
            }
            case "file": {
                this.context.setShared("repository.client", new FileRepository<>(new ClientValidator(), "./resources/clients.txt", Client.class));
                this.context.setShared("repository.webdomain", new FileRepository<>(new WebDomainValidator(), "./resources/webdomains.txt", WebDomain.class));
                this.context.setShared("repository.rental", new FileRepository<>(new RentalValidator(), "./resources/rentals.txt", Rental.class));
                break;
            }
            case "xml": {
                this.context.setShared("repository.client", new XMLRepository<>(new ClientValidator(), "./resources/clients.xml", Client.class));
                this.context.setShared("repository.webdomain", new XMLRepository<>(new WebDomainValidator(), "./resources/webdomains.xml", WebDomain.class));
                this.context.setShared("repository.rental", new XMLRepository<>(new RentalValidator(), "./resources/rentals.xml", Rental.class));
                break;
            }
            case "db": {
                String url = "jdbc:postgresql://localhost:5432/WebDomainRental";
                String username = "postgres";
                String password = "root";
                this.context.setShared("repository.client", new ClientDatabaseRepository(new ClientValidator(), url, username, password));
                this.context.setShared("repository.webdomain", new WebDomainDatabaseRepository(new WebDomainValidator(), url, username, password));
                this.context.setShared("repository.rental", new RentalDatabaseRepository(new RentalValidator(), url, username, password));
                break;
            }
            default: throw new CommandException("Invalid repository type.");
        }

        this.controller.init(this.context);
    }
}

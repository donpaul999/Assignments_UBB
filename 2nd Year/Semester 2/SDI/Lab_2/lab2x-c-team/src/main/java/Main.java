import controller.CommandService;
import controller.Controller;
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
import repository.database.DatabaseRepository;
import repository.database.RentalDatabaseRepository;
import repository.database.WebDomainDatabaseRepository;
import ui.ApplicationContext;
import ui.Console;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ApplicationContext context = createContext();

        CommandService commandService = context.getService(CommandService.class).get();
        commandService.init(context);

        try {
            commandService.loadCommands();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Controller controller = context.getService(Controller.class).get();
        controller.init(context);

        Console console = new Console(context);
        console.show();
    }

    private static ApplicationContext createContext() throws SQLException {
        ApplicationContext context = new ApplicationContext();
        context.setShared("repository.client", new InMemoryRepository<>(new ClientValidator()));
        context.setShared("repository.webdomain", new InMemoryRepository<>(new WebDomainValidator()));
        context.setShared("repository.rental", new InMemoryRepository<>(new RentalValidator()));
        context.addService(new Controller());
        context.addService(new CommandService());
        return context;
    }
}

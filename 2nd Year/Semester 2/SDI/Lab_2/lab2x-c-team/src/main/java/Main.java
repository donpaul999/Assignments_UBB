import controller.Controller;
import model.Client;
import model.Domain;
import model.Rental;
import model.validators.DummyValidator;
import repository.InMemoryRepository;
import repository.Repository;
import ui.Console;
import ui.commands.CreateClientCommand;
import ui.commands.CreateDomainCommand;
import ui.commands.CreateRentalCommand;
import ui.commands.ExitCommand;

public class Main {
    public static void main(String args[]) {
        Repository<Long, Client> clientRepository = new InMemoryRepository<>(new DummyValidator<>());
        Repository<Long, Domain> domainRepository = new InMemoryRepository<>(new DummyValidator<>());
        Repository<Long, Rental> rentalRepository = new InMemoryRepository<>(new DummyValidator<>());

        Controller controller = new Controller(clientRepository, domainRepository, rentalRepository);

        Console console = new Console();
        console.addCommand(new ExitCommand("0", "Exit"));
        console.addCommand(new CreateClientCommand("11", "Create Client", controller));
        console.addCommand(new CreateDomainCommand("12", "Create Domain", controller));
        console.addCommand(new CreateRentalCommand("13", "Create Rental", controller));

        console.show();
    }
}

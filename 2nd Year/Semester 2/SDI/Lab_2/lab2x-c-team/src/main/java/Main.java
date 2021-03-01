import controller.Controller;
import model.Client;
import model.Domain;
import model.Rental;
import model.validators.ClientValidator;
import model.validators.DomainValidator;
import model.validators.RentalValidator;
import repository.InMemoryRepository;
import repository.Repository;
import ui.Console;
import ui.commands.*;
import ui.commands.delete.DeleteClientCommand;
import ui.commands.delete.DeleteDomainCommand;
import ui.commands.delete.DeleteRentalCommand;

public class Main {
    public static void main(String[] args) {
        Repository<Long, Client> clientRepository = new InMemoryRepository<>(new ClientValidator());
        Repository<Long, Domain> domainRepository = new InMemoryRepository<>(new DomainValidator());
        Repository<Long, Rental> rentalRepository = new InMemoryRepository<>(new RentalValidator());

        Controller controller = new Controller(clientRepository, domainRepository, rentalRepository);

        Console console = new Console();
        console.addCommand(new ExitCommand("0", "Exit"));

        console.addCommand(new EmptyCommand("10"));
        console.addCommand(new CreateClientCommand("11", "Create Client", controller));
        console.addCommand(new CreateDomainCommand("12", "Create Domain", controller));
        console.addCommand(new CreateRentalCommand("13", "Create Rental", controller));

        console.addCommand(new EmptyCommand("20"));
        console.addCommand(new PrintClientsCommand("21", "Print clients", controller));
        console.addCommand(new PrintDomainsCommand("22", "Print domains", controller));
        console.addCommand(new PrintRentalsCommand("23", "Print rentals", controller));

        console.addCommand(new EmptyCommand("30"));
        console.addCommand(new UpdateClientCommand("31", "Update Client", controller));
        console.addCommand(new UpdateDomainCommand("32", "Update Domain", controller));
        console.addCommand(new UpdateRentalCommand("33", "Update Rental", controller));

        console.addCommand(new EmptyCommand("40"));
        console.addCommand(new DeleteClientCommand("41", "Delete Client", controller));
        console.addCommand(new DeleteDomainCommand("42", "Delete Domain", controller));
        console.addCommand(new DeleteRentalCommand("43", "Delete Rental", controller));

        console.show();
    }
}

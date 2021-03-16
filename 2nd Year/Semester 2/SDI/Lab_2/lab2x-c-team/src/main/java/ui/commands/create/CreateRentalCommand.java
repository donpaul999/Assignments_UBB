package ui.commands.create;

import controller.Controller;
import exceptions.CommandException;
import model.Rental;
import ui.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.sql.SQLException;
import java.util.Deque;
import java.util.Optional;

@Command(
        key = "create",
        description = "Create a new rental.",
        usage = "rentals create <id> <client_id> <domain_id> <start_date(dd-mm-yyyy)> <duration>",
        group = "rentals"
)
public class CreateRentalCommand extends BaseCommand {
    private Controller controller;

    public CreateRentalCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void init(ApplicationContext context) {
        this.controller = context.getService(Controller.class).orElse(null);
    }

    @Override
    public void execute(Deque<String> args) throws SQLException {
        Optional.of(args.size())
                .filter(s -> s == 5)
                .orElseThrow(() -> new CommandException(String.format("Invalid number of parameters. Expected %d found %d", 5, args.size())));

        Rental rental = new Rental();
        rental.setId(Long.parseLong(args.pop()));
        rental.setClientId(Long.parseLong(args.pop()));
        rental.setDomainId(Long.parseLong(args.pop()));
        rental.setStartDate(args.pop());
        rental.setDuration(Integer.parseInt(args.pop()));

        this.controller.addRental(rental);
    }
}

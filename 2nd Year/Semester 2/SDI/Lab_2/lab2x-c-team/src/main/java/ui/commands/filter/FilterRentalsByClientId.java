package ui.commands.filter;

import controller.Controller;
import exceptions.CommandException;
import ui.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.sql.SQLException;
import java.util.Deque;
import java.util.Optional;

@Command(
        key = "clientId",
        description = "Print rentals with a client id.",
        usage = "rentals filter clientId <client id>",
        group = "rentals.filter"
)
public class FilterRentalsByClientId extends BaseCommand {
    private Controller controller;

    public FilterRentalsByClientId(String key, String description) {
        super(key, description);
    }

    @Override
    public void init(ApplicationContext context) {
        this.controller = context.getService(Controller.class).orElse(null);
    }

    @Override
    public void execute(Deque<String> args) throws SQLException {
        Optional.of(args.size())
                .filter(s -> s == 1)
                .orElseThrow(() -> new CommandException(String.format("Invalid number of parameters. Expected %d found %d", 1, args.size())));

        Long id = Long.parseLong(args.pop());
        this.controller.filterRentalsByClientId(id).stream()
                .forEach(rental -> System.out.printf("%d. %d, %s, %d%n", rental.getId(), rental.getDomainId(), rental.getStartDate(), rental.getDuration()));
    }
}

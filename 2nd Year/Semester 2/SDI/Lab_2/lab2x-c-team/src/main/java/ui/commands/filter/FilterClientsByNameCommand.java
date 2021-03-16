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
        key = "filter",
        description = "Print clients with a matching name.",
        usage = "clients filter <name>",
        group = "clients"
)
public class FilterClientsByNameCommand extends BaseCommand {
    private Controller controller;

    public FilterClientsByNameCommand(String key, String description) {
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

        String clientName = args.pop();
        this.controller.filterClientByName(clientName).stream()
                .forEach(client -> System.out.printf("%d. %s%n", client.getId(), client.toString()));
    }
}

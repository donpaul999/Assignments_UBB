package ui.commands.create;

import controller.Controller;
import exceptions.CommandException;
import model.Client;
import ui.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.sql.SQLException;
import java.util.Deque;
import java.util.Optional;

@Command(
        key = "create",
        description = "Create a new client.",
        usage = "clients create <id> <name> <is_business(yes/no)>",
        group = "clients"
)
public class CreateClientCommand extends BaseCommand {
    private Controller controller;

    public CreateClientCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void init(ApplicationContext context) {
        this.controller = context.getService(Controller.class).orElse(null);
    }

    @Override
    public void execute(Deque<String> args) throws SQLException {
        Optional.of(args.size())
                .filter(s -> s == 3)
                .orElseThrow(() -> new CommandException(String.format("Invalid number of parameters. Expected %d found %d", 3, args.size())));

        Client client = new Client();
        client.setId(Long.parseLong(args.pop()));
        client.setName(args.pop());
        client.setIsBusiness(args.pop().equalsIgnoreCase("YES"));

        this.controller.addClient(client);
    }
}

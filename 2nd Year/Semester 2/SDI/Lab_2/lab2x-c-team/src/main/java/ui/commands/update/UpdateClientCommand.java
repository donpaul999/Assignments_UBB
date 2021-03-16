package ui.commands.update;

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
        key = "update",
        description = "Update a client.",
        usage = "clients update <id> <new_name> <new_is_business(yes/no)>",
        group = "clients"
)
public class UpdateClientCommand extends BaseCommand {
    private Controller controller;

    public UpdateClientCommand(String key, String description) {
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

        this.controller.updateClient(client);
    }
}

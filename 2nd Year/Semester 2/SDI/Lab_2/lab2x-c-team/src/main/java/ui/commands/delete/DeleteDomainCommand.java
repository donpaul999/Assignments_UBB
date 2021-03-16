package ui.commands.delete;

import controller.Controller;
import exceptions.CommandException;
import ui.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.sql.SQLException;
import java.util.Deque;
import java.util.Optional;

@Command(
        key = "delete",
        description = "Delete a domain.",
        usage = "domains delete <id>",
        group = "domains"
)
public class DeleteDomainCommand extends BaseCommand {
    private Controller controller;

    public DeleteDomainCommand(String key, String description) {
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

        controller.deleteDomain(Long.parseLong(args.pop()));
    }
}

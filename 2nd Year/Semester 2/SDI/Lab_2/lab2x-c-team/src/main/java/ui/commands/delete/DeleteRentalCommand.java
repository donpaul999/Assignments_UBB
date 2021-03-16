package ui.commands.delete;

import controller.Controller;
import exceptions.CommandException;
import ui.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Deque;
import java.util.Optional;

@Command(
        key = "delete",
        description = "Delete a rental.",
        usage = "rentals delete <id>",
        group = "rentals"
)
public class DeleteRentalCommand extends BaseCommand {
    private Controller controller;

    public DeleteRentalCommand(String key, String description) {
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

        controller.deleteRental(Long.parseLong(args.pop()));
    }
}

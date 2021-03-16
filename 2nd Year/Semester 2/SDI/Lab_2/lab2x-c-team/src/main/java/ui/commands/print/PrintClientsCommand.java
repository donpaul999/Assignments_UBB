package ui.commands.print;

import controller.Controller;
import model.Client;
import ui.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.Deque;

@Command(
        key = "print",
        description = "Print all the clients.",
        group = "clients"
)
public class PrintClientsCommand extends BaseCommand {
    private Controller controller;

    public PrintClientsCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void init(ApplicationContext context) {
        this.controller = context.getService(Controller.class).orElse(null);
    }

    @Override
    public void execute(Deque<String> args) throws SQLException {
        this.controller.getClients().stream()
                .sorted(Comparator.comparing(Client::getId))
                .forEach(client -> System.out.printf("%d. %s%n", client.getId(), client.toString()));
    }
}

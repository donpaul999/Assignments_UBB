package ui.commands.report;

import controller.Controller;
import ui.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.sql.SQLException;
import java.util.Deque;

@Command(
        key = "clients",
        description = "Print the most renting clients.",
        usage = "report clients",
        group = "report"
)
public class MostRentingClientsCommand extends BaseCommand {
    private Controller controller;

    public MostRentingClientsCommand(String key, String description) {
        super (key, description);
    }

    @Override
    public void init(ApplicationContext context) {
        this.controller = context.getService(Controller.class).orElse(null);
    }

    @Override
    public void execute(Deque<String> args) throws SQLException {
        controller.getMostRentingClients().forEach(System.out::println);
    }
}

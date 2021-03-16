package ui.commands.report;

import controller.Controller;
import ui.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.sql.SQLException;
import java.util.Deque;

@Command(
        key = "domains",
        description = "Print the most rented domains.",
        usage = "report domains",
        group = "report"
)
public class MostRentedDomainsCommand extends BaseCommand {
    private Controller controller;

    public MostRentedDomainsCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void init(ApplicationContext context) {
        this.controller = context.getService(Controller.class).orElse(null);
    }

    @Override
    public void execute(Deque<String> args) throws SQLException {
        controller.getMostRentedDomains().forEach(System.out::println);
    }
}

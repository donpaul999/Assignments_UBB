package ui.commands.report;

import controller.Controller;
import ui.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.sql.SQLException;
import java.util.Deque;

@Command(
        key = "years",
        description = "Print the years with the most rentals.",
        usage = "report years",
        group = "report"
)
public class MostRentedYearsCommand extends BaseCommand {
    private Controller controller;

    public MostRentedYearsCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void init(ApplicationContext context) {
        this.controller = context.getService(Controller.class).orElse(null);
    }

    @Override
    public void execute(Deque<String> args) throws SQLException {
        controller.getMostRentedYears().forEach(System.out::println);
    }
}

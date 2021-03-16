package ui.commands.report;

import controller.Controller;
import ui.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.sql.SQLException;
import java.util.Deque;

@Command(
        key = "tld",
        description = "Print the most common TLD.",
        usage = "report tld",
        group = "report"
)
public class MostCommonTldCommand extends BaseCommand {
    private Controller controller;

    public MostCommonTldCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void init(ApplicationContext context) {
        this.controller = context.getService(Controller.class).orElse(null);
    }

    @Override
    public void execute(Deque<String> args) throws SQLException {
        controller.getMostCommonTld().forEach(System.out::println);
    }
}

package ui.commands.print;

import controller.Controller;
import model.WebDomain;
import ui.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.Deque;

@Command(
        key = "print",
        description = "Print all the domains.",
        group = "domains"
)
public class PrintDomainsCommand extends BaseCommand {
    private Controller controller;

    public PrintDomainsCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void init(ApplicationContext context) {
        this.controller = context.getService(Controller.class).orElse(null);
    }

    @Override
    public void execute(Deque<String> args) throws SQLException {
        this.controller.getDomains().stream()
                .sorted(Comparator.comparing(WebDomain::getId))
                .forEach(domain -> System.out.printf("%d. %s%n", domain.getId(), domain.toString()));
    }
}

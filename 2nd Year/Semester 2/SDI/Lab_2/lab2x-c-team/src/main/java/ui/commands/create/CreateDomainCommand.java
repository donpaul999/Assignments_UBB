package ui.commands.create;

import controller.Controller;
import exceptions.CommandException;
import model.WebDomain;
import ui.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.sql.SQLException;
import java.util.Deque;
import java.util.Optional;

@Command(
        key = "create",
        description = "Create a new domain.",
        usage = "domains create <id> <name> <price>",
        group = "domains"
)
public class CreateDomainCommand extends BaseCommand {
    private Controller controller;

    public CreateDomainCommand(String key, String description) {
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

        WebDomain webDomain = new WebDomain();
        webDomain.setId(Long.parseLong(args.pop()));
        webDomain.setName(args.pop());
        webDomain.setPrice(Integer.parseInt(args.pop()));

        this.controller.addDomain(webDomain);
    }
}

package ui.commands.delete;

import controllers.WebDomainController;
import exceptions.CommandException;
import org.springframework.context.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.util.Deque;
import java.util.Optional;

@Command(
        key = "delete",
        description = "Delete a domain.",
        usage = "domains delete <id>",
        group = "domains"
)
public class DeleteDomainCommand extends BaseCommand {
    private WebDomainController webDomainService;

    public DeleteDomainCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void init(ApplicationContext context) {
        this.webDomainService = context.getBean(WebDomainController.class);
    }

    @Override
    public void execute(Deque<String> args) {
        Optional.of(args.size())
                .filter(s -> s == 1)
                .orElseThrow(() -> new CommandException(String.format("Invalid number of parameters. Expected %d found %d", 1, args.size())));

        this.webDomainService.deleteWebDomain(Long.parseLong(args.pop()));
    }
}

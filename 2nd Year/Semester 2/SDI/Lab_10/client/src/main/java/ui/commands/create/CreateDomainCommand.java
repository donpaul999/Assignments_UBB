package ui.commands.create;

import controllers.WebDomainController;
import dto.WebDomainDTO;
import exceptions.CommandException;
import org.springframework.context.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.util.Deque;
import java.util.Optional;

@Command(
        key = "create",
        description = "Create a new domain.",
        usage = "domains create <id> <name> <price>",
        group = "domains"
)
public class CreateDomainCommand extends BaseCommand {
    private WebDomainController webDomainService;

    public CreateDomainCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void init(ApplicationContext context) {
        this.webDomainService = context.getBean(WebDomainController.class);
    }

    @Override
    public void execute(Deque<String> args) {
        Optional.of(args.size())
                .filter(s -> s == 3)
                .orElseThrow(() -> new CommandException(String.format("Invalid number of parameters. Expected %d found %d", 3, args.size())));

        WebDomainDTO webDomain = new WebDomainDTO();
        webDomain.setId(Long.parseLong(args.pop()));
        webDomain.setName(args.pop());
        webDomain.setPrice(Integer.parseInt(args.pop()));

        this.webDomainService.addWebDomain(webDomain);
    }
}

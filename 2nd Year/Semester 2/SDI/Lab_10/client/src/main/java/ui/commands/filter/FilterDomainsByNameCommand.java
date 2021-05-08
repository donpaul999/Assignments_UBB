package ui.commands.filter;

import controllers.WebDomainController;
import exceptions.CommandException;
import org.springframework.context.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.util.Deque;
import java.util.Optional;

@Command(
        key = "filter",
        description = "Print domains with a matching name.",
        usage = "domains filter <name>",
        group = "domains"
)
public class FilterDomainsByNameCommand extends BaseCommand {
    private WebDomainController webDomainService;

    public FilterDomainsByNameCommand(String key, String description) {
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

        String domainName = args.pop();
        this.webDomainService.filterWebDomainsByName(domainName).getWebdomains().stream()
                .forEach(domain -> System.out.printf("%d. %s%n", domain.getId(), domain.toString()));
    }
}
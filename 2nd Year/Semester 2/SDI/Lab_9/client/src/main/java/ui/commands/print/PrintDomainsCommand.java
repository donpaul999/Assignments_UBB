package ui.commands.print;

import controllers.WebDomainController;
import dto.WebDomainDTO;
import org.springframework.context.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.util.Comparator;
import java.util.Deque;

@Command(
        key = "print",
        description = "Print all the domains.",
        group = "domains"
)
public class PrintDomainsCommand extends BaseCommand {
    private WebDomainController webDomainService;
    private Object ExecutionException;
    private Object InterruptedException;

    public PrintDomainsCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void init(ApplicationContext context) {
        this.webDomainService = context.getBean(WebDomainController.class);
    }

    @Override
    public void execute(Deque<String> args) {
        this.webDomainService.getWebDomains().getWebdomains().stream()
                .sorted(Comparator.comparing(WebDomainDTO::getId))
                .forEach(domain -> System.out.printf("%d. %s%n", domain.getId(), domain.toString()));
    }
}

package ui.commands.sort;

import controllers.WebDomainController;
import dto.WebDomainDTO;
import controllers.ClientController;
import org.springframework.context.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.util.Comparator;
import java.util.Deque;

@Command(
        key = "sort",
        description = "Sorts all the domains in ascending order by price.",
        group = "domains"
)
public class SortDomainsAscendingByPriceCommand extends BaseCommand {
    private WebDomainController webDomainService;

    public SortDomainsAscendingByPriceCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void init(ApplicationContext context) {
        this.webDomainService = context.getBean(WebDomainController.class);
    }

    @Override
    public void execute(Deque<String> args) {
        this.webDomainService.sortAscendingByDomainPrice().getWebdomains().stream()
                .sorted(Comparator.comparing(WebDomainDTO::getId))
                .forEach(webDomain -> System.out.printf("%d. %s%n", webDomain.getId(), webDomain.toString()));
    }
}

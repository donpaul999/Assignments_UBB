package ui.commands.report;

import controllers.RentalController;
import org.springframework.context.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.util.Deque;

@Command(
        key = "domains",
        description = "Print the most rented domains.",
        usage = "report domains",
        group = "report"
)
public class MostRentedDomainsCommand extends BaseCommand {
    private RentalController rentalService;

    public MostRentedDomainsCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void init(ApplicationContext context) {
        this.rentalService = context.getBean(RentalController.class);
    }

    @Override
    public void execute(Deque<String> args) {
        this.rentalService.getMostRentedDomains().getWebdomains().stream()
                .forEach(domain -> System.out.printf("%d. %s%n", domain.getId(), domain.toString()));
    }
}

package ui.commands.report;

import controllers.RentalController;
import org.springframework.context.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.util.Deque;

@Command(
        key = "clients",
        description = "Print the most renting clients.",
        usage = "report clients",
        group = "report"
)
public class MostRentingClientsCommand extends BaseCommand {
    private RentalController rentalService;

    public MostRentingClientsCommand(String key, String description) {
        super (key, description);
    }

    @Override
    public void init(ApplicationContext context) {
        this.rentalService = context.getBean(RentalController.class);
    }

    @Override
    public void execute(Deque<String> args) {
            this.rentalService.getMostRentingClients().getClients()
                    .stream()
                    .forEach(client -> System.out.printf("%d. %s%n", client.getId(), client.toString()));
    }
}
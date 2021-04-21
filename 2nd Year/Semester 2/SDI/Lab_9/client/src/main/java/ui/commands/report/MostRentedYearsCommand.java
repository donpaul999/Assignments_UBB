package ui.commands.report;

import controllers.RentalController;
import org.springframework.context.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.util.Deque;

@Command(
        key = "years",
        description = "Print the years with the most rentals.",
        usage = "report years",
        group = "report"
)
public class MostRentedYearsCommand extends BaseCommand {
    private RentalController rentalService;

    public MostRentedYearsCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void init(ApplicationContext context) {
        this.rentalService = context.getBean(RentalController.class);
    }

    @Override
    public void execute(Deque<String> args) {
        this.rentalService.getMostRentedYears()
                .stream()
                .forEach(System.out::println);
    }
}

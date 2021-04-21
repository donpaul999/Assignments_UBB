package ui.commands.report;

import controllers.RentalController;
import org.springframework.context.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.util.Deque;

@Command(
        key = "tld",
        description = "Print the most common TLD.",
        usage = "report tld",
        group = "report"
)
public class MostCommonTldCommand extends BaseCommand {
    private RentalController rentalService;

    public MostCommonTldCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void init(ApplicationContext context) {
        this.rentalService = context.getBean(RentalController.class);
    }

    @Override
    public void execute(Deque<String> args) {
        this.rentalService.getMostCommonTld()
                .stream()
                .forEach(System.out::println);
    }
}

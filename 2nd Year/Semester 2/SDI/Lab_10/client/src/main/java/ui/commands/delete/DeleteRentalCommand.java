package ui.commands.delete;

import controllers.RentalController;
import exceptions.CommandException;
import org.springframework.context.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.util.Deque;
import java.util.Optional;

@Command(
        key = "delete",
        description = "Delete a rental.",
        usage = "rentals delete <id>",
        group = "rentals"
)
public class DeleteRentalCommand extends BaseCommand {
    private RentalController rentalService;

    public DeleteRentalCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void init(ApplicationContext context) {
        this.rentalService = context.getBean(RentalController.class);
    }

    @Override
    public void execute(Deque<String> args) {
        Optional.of(args.size())
                .filter(s -> s == 1)
                .orElseThrow(() -> new CommandException(String.format("Invalid number of parameters. Expected %d found %d", 1, args.size())));

        this.rentalService.deleteRental(Long.parseLong(args.pop()));
    }
}

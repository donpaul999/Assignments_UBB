package ui.commands.create;

import controllers.RentalController;
import dto.RentalDTO;
import exceptions.CommandException;
import org.springframework.context.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.util.Deque;
import java.util.Optional;

@Command(
        key = "create",
        description = "Create a new rental.",
        usage = "rentals create <id> <client_id> <domain_id> <start_date(dd-mm-yyyy)> <duration>",
        group = "rentals"
)
public class CreateRentalCommand extends BaseCommand {
    private RentalController rentalService;

    public CreateRentalCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void init(ApplicationContext context) {
        this.rentalService = context.getBean(RentalController.class);
    }

    @Override
    public void execute(Deque<String> args) {
        Optional.of(args.size())
                .filter(s -> s == 5)
                .orElseThrow(() -> new CommandException(String.format("Invalid number of parameters. Expected %d found %d", 5, args.size())));

        RentalDTO rental = new RentalDTO();
        rental.setId(Long.parseLong(args.pop()));
        rental.setClientId(Long.parseLong(args.pop()));
        rental.setDomainId(Long.parseLong(args.pop()));
        rental.setStartDate(args.pop());
        rental.setDuration(Integer.parseInt(args.pop()));

        this.rentalService.addRental(rental);
    }
}

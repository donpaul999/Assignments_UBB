package ui.commands.update;

import controllers.RentalController;
import dto.RentalDTO;
import exceptions.CommandException;
import org.springframework.context.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.util.Deque;
import java.util.Optional;

@Command(
        key = "update",
        description = "Update a rental.",
        usage = "rentals update <id> <new_client_id> <new_domain_id> <new_start_date(dd-mm-yyyy)> <new_duration>",
        group = "rentals"
)
public class UpdateRentalCommand extends BaseCommand {
    private RentalController rentalService;

    public UpdateRentalCommand(String key, String description) {
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

        this.rentalService.updateRental(rental);
    }
}

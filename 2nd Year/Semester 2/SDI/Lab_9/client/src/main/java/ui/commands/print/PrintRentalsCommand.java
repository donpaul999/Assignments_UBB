package ui.commands.print;

import controllers.ClientController;
import controllers.RentalController;
import controllers.WebDomainController;
import dto.ClientDTO;
import dto.RentalDTO;
import dto.WebDomainDTO;
import org.springframework.context.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.util.Comparator;
import java.util.Deque;

@Command(
        key = "print",
        description = "Print all the rentals.",
        group = "rentals"
)
public class PrintRentalsCommand extends BaseCommand {
    private RentalController rentalService;
    private ClientController clientService;
    private WebDomainController webDomainService;

    public PrintRentalsCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void init(ApplicationContext context) {
        this.rentalService = context.getBean(RentalController.class);
        this.clientService = context.getBean(ClientController.class);
        this.webDomainService = context.getBean(WebDomainController.class);
    }

    @Override
    public void execute(Deque<String> args)  {
        this.rentalService.getRentals().getRentals()
                .stream()
                .sorted(Comparator.comparing(RentalDTO::getId))
                .forEach(rental -> {
                    ClientDTO client = this.clientService.getClient(rental.getClientId());
                    WebDomainDTO domain = this.webDomainService.getWebDomain(rental.getDomainId());

                    String rentalString = String.format("Rental { Client='%s', Domain='%s', Start Date=%s, Duration=%d }",
                            client.getName(),
                            domain.getName(),
                            rental.getStartDate(),
                            rental.getDuration()
                    );

                    System.out.printf("%d. %s%n", rental.getId(), rentalString);
                });
    }
}

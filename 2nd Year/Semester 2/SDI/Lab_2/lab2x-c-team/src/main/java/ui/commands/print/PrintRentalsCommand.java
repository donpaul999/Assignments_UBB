package ui.commands.print;

import controller.Controller;
import model.Rental;
import ui.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.Deque;

@Command(
        key = "print",
        description = "Print all the rentals.",
        group = "rentals"
)
public class PrintRentalsCommand extends BaseCommand {
    private Controller controller;

    public PrintRentalsCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void init(ApplicationContext context) {
        this.controller = context.getService(Controller.class).orElse(null);
    }

    @Override
    public void execute(Deque<String> args) throws SQLException {
        this.controller.getRentals().stream()
                .sorted(Comparator.comparing(Rental::getId))
                .forEach(rental -> System.out.printf("%d. %s%n", rental.getId(), rental.toString()));
    }
}

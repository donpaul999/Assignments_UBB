package ui.commands;

import controller.Controller;

public class PrintRentalsCommand extends Command {
    private final Controller controller;

    public PrintRentalsCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        this.controller.getRentals().stream()
                .forEach(rental -> System.out.printf("%d. %s%n", rental.getId(), rental.toString()));
    }
}

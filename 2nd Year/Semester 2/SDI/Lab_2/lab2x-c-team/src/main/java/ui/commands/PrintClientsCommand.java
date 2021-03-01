package ui.commands;

import controller.Controller;

public class PrintClientsCommand extends Command {
    private final Controller controller;

    public PrintClientsCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        this.controller.getClients().stream()
                .forEach(client -> System.out.printf("%d. %s%n", client.getId(), client.toString()));
    }
}

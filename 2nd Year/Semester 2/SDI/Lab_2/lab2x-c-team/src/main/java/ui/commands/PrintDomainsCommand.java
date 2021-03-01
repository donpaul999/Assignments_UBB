package ui.commands;

import controller.Controller;

public class PrintDomainsCommand extends Command {
    private final Controller controller;

    public PrintDomainsCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        this.controller.getDomains().stream()
                .forEach(domain -> System.out.printf("%d. %s%n", domain.getId(), domain.toString()));
    }
}

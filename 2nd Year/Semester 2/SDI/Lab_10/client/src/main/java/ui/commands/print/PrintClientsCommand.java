package ui.commands.print;

import controllers.ClientController;
import dto.ClientDTO;
import org.springframework.context.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.util.Comparator;
import java.util.Deque;

@Command(
        key = "print",
        description = "Print all the clients.",
        group = "clients"
)
public class PrintClientsCommand extends BaseCommand {
    private ClientController clientService;

    public PrintClientsCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void init(ApplicationContext context) {
        this.clientService = context.getBean(ClientController.class);
    }

    @Override
    public void execute(Deque<String> args) {
        this.clientService.getClients().getClients().stream()
                .sorted(Comparator.comparing(ClientDTO::getId))
                .forEach(client -> System.out.printf("%d. %s%n", client.getId(), client.toString()));
    }
}
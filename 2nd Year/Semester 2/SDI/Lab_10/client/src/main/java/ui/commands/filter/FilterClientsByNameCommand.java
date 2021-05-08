package ui.commands.filter;

import controllers.ClientController;
import exceptions.CommandException;
import org.springframework.context.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.util.Deque;
import java.util.Optional;

@Command(
        key = "filter",
        description = "Print clients with a matching name.",
        usage = "clients filter <name>",
        group = "clients"
)
public class FilterClientsByNameCommand extends BaseCommand {
    private ClientController clientService;

    public FilterClientsByNameCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void init(ApplicationContext context) {
        this.clientService = context.getBean(ClientController.class);
    }

    @Override
    public void execute(Deque<String> args) {
        Optional.of(args.size())
                .filter(s -> s == 1)
                .orElseThrow(() -> new CommandException(String.format("Invalid number of parameters. Expected %d found %d", 1, args.size())));

        String clientName = args.pop();
        this.clientService.filterClientsByName(clientName).getClients().stream()
                .forEach(client -> System.out.printf("%d. %s%n", client.getId(), client.toString()));
    }
}
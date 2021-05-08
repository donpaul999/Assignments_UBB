package ui.commands.sort;

import controllers.ClientController;
import dto.ClientDTO;
import org.springframework.context.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.util.Comparator;
import java.util.Deque;

@Command(
        key = "sort",
        description = "Sorts all the clients in ascending order by name.",
        group = "clients"
)
public class SortClientsAscendingByNameCommand extends BaseCommand {
    private ClientController clientService;

    public SortClientsAscendingByNameCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void init(ApplicationContext context) {
        this.clientService = context.getBean(ClientController.class);
    }

    @Override
    public void execute(Deque<String> args) {
        this.clientService.sortAscendingByClientName().getClients().stream()
                .sorted(Comparator.comparing(ClientDTO::getId))
                .forEach(client -> System.out.printf("%d. %s%n", client.getId(), client.toString()));
    }
}

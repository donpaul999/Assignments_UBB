package ui.commands.update;

import controllers.ClientController;
import dto.ClientDTO;
import exceptions.CommandException;
import org.springframework.context.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.util.Deque;
import java.util.Optional;

@Command(
        key = "update",
        description = "Update a client.",
        usage = "clients update <id> <new_name> <new_is_business(yes/no)>",
        group = "clients"
)
public class UpdateClientCommand extends BaseCommand {
    private ClientController clientService;

    public UpdateClientCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void init(ApplicationContext context) {
        this.clientService = context.getBean(ClientController.class);
    }

    @Override
    public void execute(Deque<String> args) {
        Optional.of(args.size())
                .filter(s -> s == 3)
                .orElseThrow(() -> new CommandException(String.format("Invalid number of parameters. Expected %d found %d", 3, args.size())));

        ClientDTO client = new ClientDTO();
        client.setId(Long.parseLong(args.pop()));
        client.setName(args.pop());
        client.setIsBusiness(args.pop().equalsIgnoreCase("YES"));

        this.clientService.updateClient(client);
    }
}
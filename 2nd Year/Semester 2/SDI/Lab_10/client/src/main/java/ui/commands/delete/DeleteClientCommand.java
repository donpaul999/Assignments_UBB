package ui.commands.delete;

import controllers.ClientController;
import exceptions.CommandException;
import org.springframework.context.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;

import java.util.Deque;
import java.util.Optional;

@Command(
        key = "delete",
        description = "Delete a client.",
        usage = "clients delete <id>",
        group = "clients"
)
public class DeleteClientCommand extends BaseCommand {
    private ClientController clientService;

    public DeleteClientCommand(String key, String description) {
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

        this.clientService.deleteClient(Long.parseLong(args.pop()));
    }
}
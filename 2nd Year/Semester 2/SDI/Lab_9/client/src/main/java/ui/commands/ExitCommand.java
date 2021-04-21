package ui.commands;

import ui.annotations.Command;

import java.util.Deque;

@Command(
        key = "exit",
        description = "Exits the application."
)
public class ExitCommand extends BaseCommand {
    public ExitCommand(String key, String description){
        super(key, description);
    }

    @Override
    public void execute(Deque<String> args) {
        System.exit(0);
    }
}

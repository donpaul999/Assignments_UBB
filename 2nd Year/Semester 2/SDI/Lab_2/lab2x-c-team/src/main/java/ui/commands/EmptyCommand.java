package ui.commands;

public class EmptyCommand extends Command {
    public EmptyCommand(String key) {
        super(key, "");
    }

    @Override
    public void execute() {
    }

    @Override
    public String toString() {
        return "";
    }
}

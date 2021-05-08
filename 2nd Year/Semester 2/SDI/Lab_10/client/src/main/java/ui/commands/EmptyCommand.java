package ui.commands;

import java.util.Deque;

public class EmptyCommand extends BaseCommand {
    public EmptyCommand(String key) {
        super(key, "");
    }

    @Override
    public void execute(Deque<String> args) {
    }

    @Override
    public String toString() {
        return "";
    }
}

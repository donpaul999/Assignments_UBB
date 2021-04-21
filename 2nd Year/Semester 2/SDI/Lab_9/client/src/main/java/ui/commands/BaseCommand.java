package ui.commands;


import org.springframework.context.ApplicationContext;

import java.util.Deque;

public abstract class BaseCommand {
    private final String key;
    private final String description;
    private String usage;

    public BaseCommand(String key, String description) {
        this.key = key;
        this.description = description;
        this.usage = key;
    }

    public void init(ApplicationContext context) { }

    public abstract void execute(Deque<String> args);

    public String getKey(){
        return this.key;
    }

    public String getDescription() {
        return this.description;
    }

    public String getHelp() {
        String usage = this.usage.isBlank() ? "" : String.format("\n\tUsage : %s", this.usage);
        return String.format("%s - %s%s", this.key, this.description, usage);
    }

    public String getUsage() {
        return this.usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", this.key, this.description);
    }
}

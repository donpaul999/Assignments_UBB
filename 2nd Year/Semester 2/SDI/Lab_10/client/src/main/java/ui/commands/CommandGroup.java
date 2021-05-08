package ui.commands;

import java.util.*;

public class CommandGroup extends BaseCommand {
    private final Map<String, BaseCommand> commands;

    public CommandGroup() {
        super("", "");
        this.commands = new HashMap<>();
    }

    public CommandGroup(String key) {
        super(key, "");
        this.commands = new HashMap<>();
    }

    public Map<String, BaseCommand> getCommands() {
        return this.commands;
    }

    public Optional<BaseCommand> getCommand(String key) {
        return Optional.ofNullable(this.commands.get(key));
    }

    public void addCommand(BaseCommand command) {
        this.commands.put(command.getKey(), command);
    }

    @Override
    public void execute(Deque<String> args) {
        Optional.of(args)
                .filter(a -> !a.isEmpty())
                .ifPresentOrElse(
                        a -> {
                            String key = a.pop();
                            Optional.ofNullable(this.commands.get(key))
                                    .ifPresentOrElse(
                                            command -> {
                                                command.execute(args);
                                            },
                                            () -> System.out.println("Invalid command.")
                                    );

                        },
                        () -> System.out.println(this.getHelp())
                );
    }

    @Override
    public String getHelp() {
        String commands = this.commands
                .values()
                .stream()
                .sorted(Comparator.comparing(BaseCommand::getKey))
                .map(c -> "\t" + c.getHelp().replace("\n", "\n\t") + "\n")
                .reduce("", (result, command) -> result + command);

        return String.format("%s:\n%s", this.getKey(), commands);
    }

    @Override
    public String toString() {
        return String.format("%s", this.getKey());
    }
}

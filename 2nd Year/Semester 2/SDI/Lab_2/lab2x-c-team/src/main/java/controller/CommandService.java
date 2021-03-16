package controller;

import exceptions.CommandServiceException;
import org.reflections.Reflections;
import ui.ApplicationContext;
import ui.annotations.Command;
import ui.commands.BaseCommand;
import ui.commands.CommandGroup;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Optional;

public class CommandService {
    private final CommandGroup mainCommandGroup;
    private ApplicationContext context;

    public CommandService() {
        this.mainCommandGroup = new CommandGroup("main");
    }

    public void init(ApplicationContext context) {
        this.context = context;
    }

    public CommandGroup getMainCommandGroup() {
        return this.mainCommandGroup;
    }

    public void loadCommand(Class<?> commandClass) {
        Command commandAnnotation = commandClass.getAnnotation(Command.class);
        try {
            Constructor<?> commandConstructor = commandClass.getConstructor(String.class, String.class);
            Object obj = commandConstructor.newInstance(commandAnnotation.key(), commandAnnotation.description());
            BaseCommand command = (BaseCommand) obj;
            command.init(this.context);
            command.setUsage(commandAnnotation.usage());

            // Encapsulate the target group inside an anonymous object
            // to be able to modify it from inside a lambda
            var result = new Object() {
                CommandGroup targetGroup;
            };

            result.targetGroup = this.mainCommandGroup;

            // Iterate through all the groups in the annotation
            Arrays.stream(commandAnnotation.group().split("\\."))
                    .filter(groupKey -> !groupKey.isBlank())
                    .forEachOrdered(groupKey -> {
                        // Get the command with the current group key if it exists,
                        // create a new group if it doesn't.
                        BaseCommand groupCommand = result.targetGroup.getCommand(groupKey).orElseGet(() -> {
                            BaseCommand newGroup = new CommandGroup(groupKey);
                            result.targetGroup.addCommand(newGroup);
                            return newGroup;
                        });

                        // Check that the command is a group if it exists.
                        // Throw an exception if it exists and it's not a group.
                        result.targetGroup = Optional.of(groupCommand)
                                .filter(c -> c instanceof CommandGroup)
                                .map(c -> (CommandGroup) c)
                                .orElseThrow(() -> new CommandServiceException(String.format("%s is not a command group.", groupKey)));
                    });

            result.targetGroup.addCommand(command);
        } catch (Exception e) {
            String message = String.format(
                    "Error while loading command '%s': %s",
                    commandClass.getName(),
                    e.getMessage()
            );

            throw new CommandServiceException(message);
        }
    }

    /**
     * Loads all the commands from the ui.commands package.
     * Command classes are recognized by being annotated with the {@link Command} class
     */
    public void loadCommands() {
        Optional.ofNullable(this.context)
                .orElseThrow(() -> new CommandServiceException("Command service not initialized."));

        Reflections reflections = new Reflections("ui.commands");

        // Iterate through all the classes annotated with the Command annotation
        reflections.getTypesAnnotatedWith(Command.class)
                .stream()
                .forEach(this::loadCommand);
    }
}

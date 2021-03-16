package ui;

import controller.CommandService;
import ui.commands.BaseCommand;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class Console {
    private final CommandService commands;

    public Console(ApplicationContext context) {
        this.commands = context.getService(CommandService.class).orElseThrow();
    }

    private void printMenu() {
        this.commands.getMainCommandGroup()
                .getCommands()
                .values()
                .stream()
                .sorted(Comparator.comparing(BaseCommand::getKey))
                .forEach(System.out::println);
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("> ");
            String input = scanner.nextLine();
            Deque<String> tokens = this.tokenize(input);
            String key = tokens.pop();

            try {
                Optional.of(key)
                        .filter(k -> !k.isBlank())
                        .ifPresentOrElse(
                                k -> this.commands.getMainCommandGroup()
                                        .getCommand(key)
                                        .ifPresentOrElse(
                                                command -> {
                                                    try {
                                                        command.execute(tokens);
                                                    } catch (SQLException throwables) {
                                                        throwables.printStackTrace();
                                                    }
                                                },
                                                () -> System.out.println("Invalid command.")
                                        ),
                                () -> this.commands.getMainCommandGroup().execute(tokens)
                        );


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Deque<String> tokenize(String rawInput) {
        return Arrays.stream(rawInput.split(" +(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"))
                .map(token -> token.replace("\"", ""))
                .collect(Collectors.toCollection(ArrayDeque::new));
    }
}

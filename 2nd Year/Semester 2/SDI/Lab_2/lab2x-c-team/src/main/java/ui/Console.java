package ui;

import ui.commands.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class Console {
    private final Map<String, Command> commands;

    public Console() {
        this.commands = new HashMap<>();
    }

    private void printMenu() {
        this.commands.values().stream()
                .forEach(command -> {
                    String line=String.format("%4s. %s", command.getKey(), command.getDescription());
                    System.out.println(line);
                });
    }

    public void addCommand(Command command) {
        this.commands.put(command.getKey(), command);
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            this.printMenu();

            System.out.println("Input option: ");
            String key = scanner.nextLine();
            Command command = this.commands.get(key);

            if (command == null) {
                System.out.println("Invalid option.");
                continue;
            }

            command.execute();
        }
    }

}

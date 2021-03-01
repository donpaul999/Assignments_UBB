package ui.commands.delete;

import controller.Controller;
import ui.commands.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeleteClientCommand extends Command {
    private final Controller controller;

    public DeleteClientCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    /**
     * Reads a client id and deletes that client.
     */
    @Override
    public void execute() {
        Long clientId = readClientId();
        controller.deleteClient(clientId);
    }

    /**
     * Reads a client id from the user's input.
     * @return the client id.
     */
    private Long readClientId() {
        System.out.println("Give the client's id to delete:");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            return Long.valueOf(bufferRead.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

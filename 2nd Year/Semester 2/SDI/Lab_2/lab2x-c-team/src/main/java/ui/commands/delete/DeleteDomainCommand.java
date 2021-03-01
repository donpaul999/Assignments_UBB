package ui.commands.delete;

import controller.Controller;
import ui.commands.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeleteDomainCommand extends Command {
    private final Controller controller;

    public DeleteDomainCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    /**
     * Reads a domain id and deletes that domain.
     */
    @Override
    public void execute() {
        Long domainId = readDomainId();
        controller.deleteDomain(domainId);
    }

    /**
     * Reads a domain id from the user's input.
     * @return the domain id.
     */
    private Long readDomainId() {
        System.out.println("Give the domain's id to delete:");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            return Long.valueOf(bufferRead.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

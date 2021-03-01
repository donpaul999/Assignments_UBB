package ui.commands.delete;

import controller.Controller;
import ui.commands.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeleteRentalCommand extends Command {
    private final Controller controller;

    public DeleteRentalCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    /**
     * Reads a rental id and deletes that rental.
     */
    @Override
    public void execute() {
        Long rentalId = readRentalId();
        controller.deleteRental(rentalId);
    }

    /**
     * Reads a rental id from the user's input.
     * @return the rental id.
     */
    private Long readRentalId() {
        System.out.println("Give the rental's id to delete:");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            return Long.valueOf(bufferRead.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

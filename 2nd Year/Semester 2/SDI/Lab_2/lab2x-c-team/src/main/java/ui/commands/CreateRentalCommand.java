package ui.commands;

import controller.Controller;
import model.Domain;
import model.Rental;
import model.validators.ValidatorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class CreateRentalCommand extends Command{
    private final Controller controller;

    public CreateRentalCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    /**
     * Read and add a rental to the controller.
     */
    @Override
    public void execute() {
        Rental rental = readRental();
        try {
            controller.addRental(rental);
        } catch (ValidatorException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read & create a rental from the user's input.
     * @return rental
     */
    private Rental readRental() {
        System.out.println("Read Rental {id, client ID, domain ID, Start Date (dd-mm-yyyy), duration}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());// ...
            Long clientId = Long.valueOf(bufferRead.readLine());// ...
            Long domainId = Long.valueOf(bufferRead.readLine());// ...
            String stringDate = bufferRead.readLine();
            Integer duration = Integer.parseInt(bufferRead.readLine());// ...

            Date startDate = new SimpleDateFormat("dd-MM-yyyy").parse(stringDate);
            Rental rental = new Rental(clientId, domainId, startDate, duration);
            rental.setId(id);

            return rental;
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

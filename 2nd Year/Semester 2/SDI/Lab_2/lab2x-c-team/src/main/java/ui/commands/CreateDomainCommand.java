package ui.commands;

import controller.Controller;
import model.Domain;
import model.validators.ValidatorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateDomainCommand extends Command {
    private final Controller controller;

    public CreateDomainCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }


    /**
     * Read and add a domain to the controller.
     */
    @Override
    public void execute() {
        Domain domain = readDomain();
        try {
            controller.addDomain(domain);
        } catch (ValidatorException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read & create a domain from the user's input.
     * @return domain
     */
    private Domain readDomain() {
        System.out.println("Read Domain {id, name, price}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());// ...
            String name = bufferRead.readLine();
            Integer price = Integer.parseInt(bufferRead.readLine());// ...

            Domain domain = new Domain(name, price);
            domain.setId(id);

            return domain;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

package ui.commands;

import controller.Controller;
import model.Client;
import model.validators.ValidatorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateClientCommand extends Command {
    private final Controller controller;

    public CreateClientCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    /**
     * Read and add a client to the controller.
     */
    @Override
    public void execute() {
        Client client = readClient();
        try {
            controller.addClient(client);
        } catch (ValidatorException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read & create a client from the user's input.
     * @return client
     */
    private Client readClient() {
        System.out.println("Read client {id, name, is it business?}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());// ...
            String name = bufferRead.readLine();
            String answerBusiness = bufferRead.readLine();// ...
            boolean isBusiness = true;
            if(answerBusiness.toUpperCase() == "NO")
                isBusiness = false;

            Client client = new Client(name, isBusiness);
            client.setId(id);

            return client;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

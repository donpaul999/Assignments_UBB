package controllers;

import dto.ClientDTO;
import dto.ClientsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class ClientController implements IClientController {
    @Autowired
    private RestTemplate restTemplate;
    private final String url = "http://localhost:8080/server-web/api/clients";

    @Override
    public ClientsDTO getClients() {
        return this.restTemplate.getForObject(this.url, ClientsDTO.class);
    }

    @Override
    public ClientDTO getClient(Long id) {
        return this.restTemplate.getForObject(this.url + "/{id}", ClientDTO.class, id);
    }

    @Override
    public ResponseEntity<?> addClient(ClientDTO client){
        try {
            this.restTemplate.postForObject(this.url, client, ClientDTO.class);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteClient(Long clientId) {
        try {
            this.restTemplate.delete(this.url + "/{id}", clientId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> updateClient(ClientDTO client) {
        try {
            this.restTemplate.put(this.url, client);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ClientsDTO filterClientsByName(String name) {
        return this.restTemplate.getForObject(this.url + "/filter/{name}", ClientsDTO.class, name);
    }

    @Override
    public ClientsDTO sortAscendingByClientName() {
        return this.restTemplate.getForObject(this.url + "/sort/name", ClientsDTO.class);
    }
}

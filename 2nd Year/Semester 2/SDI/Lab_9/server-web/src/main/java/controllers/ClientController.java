package controllers;

import converter.ClientConverter;
import dto.ClientDTO;
import dto.ClientsDTO;
import model.validators.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.IClientService;

@RestController
public class ClientController implements IClientController {
    public static final Logger log= LoggerFactory.getLogger(IClientController.class);

    @Autowired
    private IClientService service;

    @Autowired
    private ClientConverter converter;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    @Override
    public ClientsDTO getClients() {
        ClientsDTO cpy = new ClientsDTO(this.converter
                .convertModelsToDTOs(this.service.getClients()));
        return cpy;
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
    @Override
    public ClientDTO getClient(@PathVariable Long id) {
        ClientDTO cpy = this.converter.convertModelToDTO(this.service.getClient(id));
        return cpy;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    @Override
    public ResponseEntity<?> addClient(@RequestBody ClientDTO client) throws ValidatorException {
        try {
            this.service.addClient(this.converter.convertDTOToModel(client));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (ValidatorException e) {
            log.error("ERROR", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    @Override
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        try {
            this.service.deleteClient(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (ValidatorException e) {
            log.error("ERROR", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/clients", method = RequestMethod.PUT)
    @Override
    public ResponseEntity<?> updateClient(@RequestBody ClientDTO client) throws ValidatorException {
        try {
            this.service.updateClient(this.converter.convertDTOToModel(client));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (ValidatorException e) {
            log.error("ERROR", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/clients/filter/{name}", method = RequestMethod.GET)
    @Override
    public ClientsDTO filterClientsByName(@PathVariable String name) {
        return new ClientsDTO(converter.convertModelsToDTOs(service.filterClientsByName(name)));
    }

    @RequestMapping(value = "/clients/sort/name", method = RequestMethod.GET)
    @Override
    public ClientsDTO sortAscendingByClientName() {
        return new ClientsDTO(converter.convertModelsToDTOs(service.sortAscendingByClientName()));
    }
}

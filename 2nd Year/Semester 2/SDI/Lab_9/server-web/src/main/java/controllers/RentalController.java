package controllers;

import converter.RentalConverter;
import dto.ClientsDTO;
import dto.RentalDTO;
import dto.RentalsDTO;
import dto.WebDomainsDTO;
import model.validators.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.IRentalService;

import java.util.Set;

@RestController
public class RentalController implements IRentalController {
    public static final Logger log= LoggerFactory.getLogger(IWebDomainController.class);

    @Autowired
    private IRentalService service;

    @Autowired
    private RentalConverter converter;

    @RequestMapping(value = "/rentals", method = RequestMethod.GET)
    @Override
    public RentalsDTO getRentals() {
        RentalsDTO cpy = new RentalsDTO(converter.convertModelsToDTOs(service.getRentals()));
        return cpy;
    }

    @RequestMapping(value = "/rentals/{id}", method = RequestMethod.GET)
    @Override
    public RentalDTO getRental(@PathVariable Long id) {
        RentalDTO cpy = converter.convertModelToDTO(service.getRental(id));
        return cpy;
    }

    @RequestMapping(value = "/rentals", method = RequestMethod.POST)
    @Override
    public ResponseEntity<?> addRental(@RequestBody RentalDTO rental) throws ValidatorException {
        try {
            service.addRental(converter.convertDTOToModel(rental));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            log.error("ERROR", e);
            return ResponseEntity.badRequest().body(e.getStackTrace());
        }
    }

    @RequestMapping(value = "/rentals/{id}", method = RequestMethod.DELETE)
    @Override
    public ResponseEntity<?> deleteRental(@PathVariable Long id) {
        try {
            service.deleteRental(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (ValidatorException e) {
            log.error("ERROR", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/rentals", method = RequestMethod.PUT)
    @Override
    public ResponseEntity<?> updateRental(@RequestBody RentalDTO rental) throws ValidatorException {
        try {
            service.updateRental(converter.convertDTOToModel(rental));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (ValidatorException e) {
            log.error("ERROR", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/rentals/filter/client/{name}", method = RequestMethod.GET)
    @Override
    public RentalsDTO findRentalsByClientName(@PathVariable String name) {
        return new RentalsDTO(converter.convertModelsToDTOs(service.filterRentalsByClientName(name)));
    }

    @RequestMapping(value = "/rentals/filter/domain/{name}", method = RequestMethod.GET)
    @Override
    public RentalsDTO findRentalsByDomainName(@PathVariable String name) {
        return new RentalsDTO(converter.convertModelsToDTOs(service.filterRentalsByDomainName(name)));
    }

    @Override
    public ClientsDTO getMostRentingClients() {
        return null;
    }

    @Override
    public WebDomainsDTO getMostRentedDomains() {
        return null;
    }

    @Override
    public Set<String> getMostRentedYears() {
        return null;
    }

    @Override
    public Set<String> getMostCommonTld() {
        return null;
    }

}

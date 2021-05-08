package controllers;

import converter.WebDomainConverter;
import dto.WebDomainDTO;
import dto.WebDomainsDTO;
import model.validators.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.IWebDomainService;

import java.util.Set;

@RestController
public class WebDomainController implements IWebDomainController {
    public static final Logger log= LoggerFactory.getLogger(IWebDomainController.class);

    @Autowired
    private IWebDomainService service;

    @Autowired
    private WebDomainConverter converter;

    @RequestMapping(value = "/domains", method = RequestMethod.GET)
    @Override
    public WebDomainsDTO getWebDomains() {
        WebDomainsDTO cpy = new WebDomainsDTO(converter
                .convertModelsToDTOs(service.getWebDomains()));
        return cpy;
    }

    @RequestMapping(value = "/domains/{id}", method = RequestMethod.GET)
    @Override
    public WebDomainDTO getWebDomain(@PathVariable Long id) {
        WebDomainDTO cpy = converter.convertModelToDTO(service.getWebDomain(id));
        return cpy;
    }

    @RequestMapping(value = "/domains", method = RequestMethod.POST)
    @Override
    public ResponseEntity<?> addWebDomain(@RequestBody WebDomainDTO webDomain) throws ValidatorException {
        try {
            service.addWebDomain(converter.convertDTOToModel(webDomain));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (ValidatorException e) {
            log.error("ERROR", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/domains/{id}", method = RequestMethod.DELETE)
    @Override
    public ResponseEntity<?> deleteWebDomain(@PathVariable Long id) {
        try {
            service.deleteWebDomain(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (ValidatorException e) {
            log.error("ERROR", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/domains", method = RequestMethod.PUT)
    @Override
    public ResponseEntity<?> updateWebDomain(@RequestBody WebDomainDTO webDomain) throws ValidatorException {
        try {
            service.updateWebDomain(converter.convertDTOToModel(webDomain));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (ValidatorException e) {
            log.error("ERROR", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/domains/filter/{name}", method = RequestMethod.GET)
    @Override
    public WebDomainsDTO filterWebDomainsByName(@PathVariable String name) {
        return new WebDomainsDTO(converter.convertModelsToDTOs(service.filterWebDomainsByName(name)));
    }

    @RequestMapping(value = "/domains/sort/price", method = RequestMethod.GET)
    @Override
    public WebDomainsDTO sortAscendingByDomainPrice() {
        return new WebDomainsDTO(converter.convertModelsToDTOs(service.sortAscendingByDomainPrice()));
    }
}

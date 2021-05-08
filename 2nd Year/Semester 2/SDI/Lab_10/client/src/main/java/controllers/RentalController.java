package controllers;

import dto.ClientsDTO;
import dto.RentalDTO;
import dto.RentalsDTO;
import dto.WebDomainsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Controller
public class RentalController implements IRentalController {
    @Autowired
    private RestTemplate restTemplate;
    private String url = "http://localhost:8080/server-web/api/rentals";

    @Override
    public RentalsDTO getRentals() {
        return restTemplate.getForObject(url, RentalsDTO.class);
    }

    @Override
    public RentalDTO getRental(Long id) {
        return restTemplate.getForObject(url + "/{id}", RentalDTO.class, id);
    }

    @Override
    public ResponseEntity<?> addRental(RentalDTO rental){
        try {
            restTemplate.postForObject(url, rental, RentalDTO.class);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteRental(Long rentalId) {
        try {
            restTemplate.delete(url + "/{id}", rentalId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> updateRental(RentalDTO rental) {
        try {
            restTemplate.put(url, rental);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public RentalsDTO findRentalsByClientName(String name) {
        return this.restTemplate.getForObject(this.url + "/filter/client/{name}", RentalsDTO.class, name);
    }

    @Override
    public RentalsDTO findRentalsByDomainName(String name) {
        return this.restTemplate.getForObject(this.url + "/filter/domain/{name}", RentalsDTO.class, name);
    }

    @Override
    public ClientsDTO getMostRentingClients () {
        return null;
    }

    @Override
    public WebDomainsDTO getMostRentedDomains () {
        return null;
    }

    @Override
    public Set<String> getMostRentedYears () {
        return null;
    }

    @Override
    public Set<String> getMostCommonTld () {
        return null;
    }
}

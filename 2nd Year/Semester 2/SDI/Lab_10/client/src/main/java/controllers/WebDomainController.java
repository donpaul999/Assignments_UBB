package controllers;

import dto.WebDomainDTO;
import dto.WebDomainsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class WebDomainController implements IWebDomainController {
    @Autowired
    private RestTemplate restTemplate;
    private String url = "http://localhost:8080/server-web/api/domains";

    @Override
    public WebDomainsDTO getWebDomains() {
        return restTemplate.getForObject(url, WebDomainsDTO.class);
    }

    @Override
    public WebDomainDTO getWebDomain(Long id) {
        return restTemplate.getForObject(url + "/{id}", WebDomainDTO.class, id);
    }

    @Override
    public ResponseEntity<?> addWebDomain(WebDomainDTO webDomain){
        try {
            restTemplate.postForObject(url, webDomain, WebDomainsDTO.class);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteWebDomain(Long domainId) {
        try {
            restTemplate.delete(url + "/{id}", domainId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> updateWebDomain(WebDomainDTO webDomain) {
        try {
            restTemplate.put(url, webDomain);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public WebDomainsDTO filterWebDomainsByName(String name) {
        return this.restTemplate.getForObject(this.url + "/filter/{name}", WebDomainsDTO.class, name);
    }

    @Override
    public WebDomainsDTO sortAscendingByDomainPrice() {
        return this.restTemplate.getForObject(this.url + "/sort/price", WebDomainsDTO.class);
    }
}

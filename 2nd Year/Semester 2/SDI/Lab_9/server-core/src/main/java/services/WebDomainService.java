package services;

import exceptions.ControllerException;
import model.Rental;
import model.WebDomain;
import model.validators.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.database.RentalDatabaseRepository;
import repository.database.WebDomainDatabaseRepository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class WebDomainService implements IWebDomainService{
    @Autowired
    private WebDomainDatabaseRepository webDomains;

    public static final Logger log = LoggerFactory.getLogger(IWebDomainService.class);

    @Autowired
    private RentalDatabaseRepository rentals;

    /**
     * Returns all the domains.
     *
     * @return the set of domains
     */
    public Set<WebDomain> getWebDomains() {
        log.trace("getWebDomains --- method entered");

        Iterable<WebDomain> domains = this.webDomains.findAll();
        Set<WebDomain> result = StreamSupport.stream(domains.spliterator(), false).collect(Collectors.toSet());

        log.trace("getWebDomains: result={}", result);
        return result;
    }

    /**
     * Returns the domain with a given id.
     *
     * @param id the id of the domain
     * @return the domain
     */
    public WebDomain getWebDomain(Long id) {
        log.trace("getWebDomain --- method entered");

        WebDomain result = this.webDomains.findById(id)
                .orElseThrow(() -> new ControllerException(String.format("Domain id not found: %d", id)));

        log.trace("getWebDomain: result={}", result);
        return result;
    }

    /**
     * Add domain to the domain repository
     *
     * @param webDomain
     * @throws ValidatorException
     */
    public void addWebDomain(WebDomain webDomain) throws ValidatorException {
        log.trace("addWebDomain --- method entered");

        this.webDomains.findById(webDomain.getId())
                .ifPresent(x -> {
                    throw new ControllerException(String.format("Domain id already exists: %d", webDomain.getId()));
                });

        this.webDomains.save(webDomain);

        String result = "Success";
        log.trace("addDomain: result={}", result);
    }

    /**
     * Delete a domain from the domain repository.
     *
     * @param domainId the domain's id to delete.
     */
    public void deleteWebDomain(Long domainId) {
        log.trace("deleteWebDomain --- method entered");

        Set<Rental> rentalsWithDomainId = this.rentals.findRentalsByDomainId(domainId);
        rentalsWithDomainId.forEach(rental -> {
            try {
                this.rentals.deleteById(rental.getId());
            } catch (Exception e) {
                throw new ControllerException(e.getMessage());
            }
        });

        this.webDomains.findById(domainId)
                .orElseThrow(() -> new ControllerException(String.format("Domain id not found: %d", domainId)));

        this.webDomains.deleteById(domainId);

        String result = "Success";
        log.trace("deleteDomain: result={}", result);
    }

    /**
     * Updates an existent domain.
     *
     * @param webDomain
     * @throws ValidatorException
     */
    public void updateWebDomain(WebDomain webDomain) throws ValidatorException {
        log.trace("updateWebDomain --- method entered");

        this.webDomains.findById(webDomain.getId())
                .orElseThrow(() -> new ControllerException(String.format("(Update) Domain id not found: %d", webDomain.getId())));

        this.webDomains.save(webDomain);

        String result = "Success";
        log.trace("updateDomain: result={}", result);
    }

    /**
     * Returns all domains with a matching or a partially matching name.
     * @param name the domain name
     * @return the set of domains with a matching or a partially matching name.
     */
    public Set<WebDomain> filterWebDomainsByName(String name) {
        log.trace("filterWebDomainByName --- method entered");

        Set<WebDomain> webDomains = this.webDomains.findByNameContaining(name);

        Set<WebDomain> result = StreamSupport.stream(this.webDomains.findAll().spliterator(), false)
                .filter(domain -> domain.getName().contains(name))
                .collect(Collectors.toSet());

        log.trace("filterWebDomainByName: result={}", result);
        return result;
    }

    /**
     * Returns all domains sorted by price in ascending order.
     * @return the set of domains
     */
    public Set<WebDomain> sortAscendingByDomainPrice() {
        log.trace("sortAscendingByDomainPrice --- method entered");
        var webDomains = this.webDomains.findAllByOrderByPriceAsc();
        log.trace("filterWebDomainByName: result={}", webDomains);
        return webDomains;
    }
}

package controllers;

import dto.WebDomainDTO;
import dto.WebDomainsDTO;
import org.springframework.http.ResponseEntity;

public interface IWebDomainController {
    /**
     * Returns all the domains.
     *
     * @return the set of domains
     */
    WebDomainsDTO getWebDomains();

    /**
     * Returns the domain with a given id.
     *
     * @param id the id of the domain
     * @return the domain
     */
    WebDomainDTO getWebDomain(Long id);

    /**
     * Add domain to the domain repository
     *
     * @param webDomain
     * @return
     */
    ResponseEntity<?> addWebDomain(WebDomainDTO webDomain);

    /**
     * Delete a domain from the domain repository.
     *
     * @param domainId the domain's id to delete.
     * @return
     */
    ResponseEntity<?> deleteWebDomain(Long domainId);

    /**
     * Updates an existent domain.
     *
     * @param webDomain
     * @return
     */
    ResponseEntity<?> updateWebDomain(WebDomainDTO webDomain);

    /**
     * Returns all domains with a matching or a partially matching name.
     * @param name the domain name
     * @return the set of domains with a matching or a partially matching name.
     */
    WebDomainsDTO filterWebDomainsByName(String name);

    /**
     * Returns all domains sorted by domain price.
     * @return the set of domains sorted by domain price.
     */
    WebDomainsDTO sortAscendingByDomainPrice();
}

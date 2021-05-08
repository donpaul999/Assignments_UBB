package services;

import model.WebDomain;
import model.validators.ValidatorException;

import java.util.Set;

public interface IWebDomainService {

    /**
     * Returns all the domains.
     *
     * @return the set of domains
     */
    Set<WebDomain> getWebDomains();

    /**
     * Returns the domain with a given id.
     *
     * @param id the id of the domain
     * @return the domain
     */
    WebDomain getWebDomain(Long id);

    /**
     * Add domain to the domain repository
     *
     * @param webDomain
     * @throws ValidatorException
     * @return
     */
    void addWebDomain(WebDomain webDomain) throws ValidatorException;

    /**
     * Delete a domain from the domain repository.
     *
     * @param domainId the domain's id to delete.
     * @return
     */
    void deleteWebDomain(Long domainId);

    /**
     * Updates an existent domain.
     *
     * @param webDomain
     * @throws ValidatorException
     * @return
     */
     void updateWebDomain(WebDomain webDomain) throws ValidatorException;

    /**
     * Returns all domains with a matching or a partially matching name.
     * @param name the domain name
     * @return the set of domains with a matching or a partially matching name.
     */
    Set<WebDomain> filterWebDomainsByName(String name);

    /**
     * Returns all domains sorted by domain price.
     * @return the set of domains sorted by domain price.
     */
    Set<WebDomain> sortAscendingByDomainPrice();
}

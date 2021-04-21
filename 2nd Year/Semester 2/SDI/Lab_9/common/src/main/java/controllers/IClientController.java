package controllers;

import dto.ClientDTO;
import dto.ClientsDTO;
import org.springframework.http.ResponseEntity;

public interface IClientController {
    /**
     * Returns all the ClientDTOs.
     *
     * @return the set of ClientDTOs
     */
    ClientsDTO getClients();

    /**
     * Returns the ClientDTO with a given id.
     *
     * @param id the id of the ClientDTO
     * @return the ClientDTO
     */
    ClientDTO getClient(Long id);

    /**
     * Add ClientDTO to the ClientDTO repository
     *
     * @param ClientDTO
     */
    ResponseEntity<?> addClient(ClientDTO ClientDTO);

    /**
     * Delete a ClientDTO from the ClientDTO repository.
     *
     * @param ClientDTOId the ClientDTO's id to delete.
     */
    ResponseEntity<?> deleteClient(Long ClientDTOId);


    /**
     * Updates an existent ClientDTO.
     *
     * @param ClientDTO
     */
    ResponseEntity<?> updateClient(ClientDTO ClientDTO);

    /**
     * Returns all ClientDTOs with a matching or a partial matching name.
     * @param name the ClientDTO name
     * @return the set of ClientDTOs with a matching or a partially matching name.
     */
    ClientsDTO filterClientsByName(String name);

    /**
     * Returns all ClientDTOs sorted by name.
     * @return the set of ClientDTOs sorted by name.
     */
    ClientsDTO sortAscendingByClientName();
}

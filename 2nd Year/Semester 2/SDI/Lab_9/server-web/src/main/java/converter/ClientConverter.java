package converter;

import dto.ClientDTO;
import model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter extends BaseConverter<Long, Client, ClientDTO> {
    @Override
    public Client convertDTOToModel(ClientDTO dto) {
        Client model = new Client();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setIsBusiness(dto.getIsBusiness());

        return model;
    }

    @Override
    public ClientDTO convertModelToDTO(Client model) {
        ClientDTO dto = new ClientDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setIsBusiness(model.getIsBusiness());

        return dto;
    }
}

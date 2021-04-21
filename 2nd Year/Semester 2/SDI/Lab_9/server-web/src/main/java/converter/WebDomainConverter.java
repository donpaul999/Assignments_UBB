package converter;

import dto.WebDomainDTO;
import model.WebDomain;
import org.springframework.stereotype.Component;

@Component
public class WebDomainConverter extends BaseConverter<Long, WebDomain, WebDomainDTO> {

    @Override
    public WebDomain convertDTOToModel(WebDomainDTO dto) {
        WebDomain webDomain = new WebDomain();
        webDomain.setId(dto.getId());
        webDomain.setName(dto.getName());
        webDomain.setPrice(dto.getPrice());

        return webDomain;
    }

    @Override
    public WebDomainDTO convertModelToDTO(WebDomain model) {
        WebDomainDTO webDomainDTO = new WebDomainDTO();
        webDomainDTO.setId(model.getId());
        webDomainDTO.setName(model.getName());
        webDomainDTO.setPrice(model.getPrice());

        return webDomainDTO;
    }
}

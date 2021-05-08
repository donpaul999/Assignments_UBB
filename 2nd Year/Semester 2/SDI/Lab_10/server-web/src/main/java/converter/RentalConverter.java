package converter;

import dto.RentalDTO;
import model.Rental;
import org.springframework.stereotype.Component;

@Component
public class RentalConverter extends BaseConverter<Long, Rental, RentalDTO> {
    @Override
    public Rental convertDTOToModel(RentalDTO dto) {
        Rental rental = new Rental();
        rental.setId(dto.getId());
        rental.setClientId(dto.getClientId());
        rental.setDomainId(dto.getDomainId());
        rental.setDuration(dto.getDuration());
        rental.setStartDate(dto.getStartDate());

        return rental;
    }

    @Override
    public RentalDTO convertModelToDTO(Rental model) {
        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setId(model.getId());
        rentalDTO.setClientId(model.getClientId());
        rentalDTO.setDomainId(model.getDomainId());
        rentalDTO.setDuration(model.getDuration());
        rentalDTO.setStartDate(model.getStartDate());

        return rentalDTO;
    }
}

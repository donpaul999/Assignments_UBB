package dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data()
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ClientDTO extends BaseDTO {
    private String name;
    private Boolean isBusiness;
}

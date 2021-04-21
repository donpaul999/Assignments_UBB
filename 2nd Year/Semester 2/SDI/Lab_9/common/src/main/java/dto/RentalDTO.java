package dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data()
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RentalDTO extends BaseDTO {
    private Long clientId;
    private Long domainId;
    private String startDate;
    private Integer duration;
}

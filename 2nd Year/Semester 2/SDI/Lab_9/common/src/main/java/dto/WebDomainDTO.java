package dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data()
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WebDomainDTO extends BaseDTO {
    private String name;
    private Integer price;
}

package kg.tili.kgserver.dto;

import lombok.*;

/**
 * @author Markitanov Vadim
 * @since 05.04.2023
 */
@Data
@Builder
@AllArgsConstructor
public class TypeDto {
    private Long value;
    private String label;
}

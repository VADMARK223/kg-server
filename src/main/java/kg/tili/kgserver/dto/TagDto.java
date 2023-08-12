package kg.tili.kgserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Markitanov Vadim
 * @since 05.04.2023
 */
@Data
@Builder
@AllArgsConstructor
public class TagDto {
    private Long value;
    private String label;
    private String color;
}

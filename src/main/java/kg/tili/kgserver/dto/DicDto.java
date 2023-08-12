package kg.tili.kgserver.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Markitanov Vadim
 * @since 05.04.2023
 */
@Getter
@Setter
public class DicDto {
    private List<TagDto> tags = new ArrayList<>();
    private List<TypeDto> types = new ArrayList<>();
    private List<WordDto> words = new ArrayList<>();
}

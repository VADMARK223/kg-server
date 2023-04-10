package kg.tili.kgserver.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Markitanov Vadim
 * @since 05.04.2023
 */
public class DicDto {
    public List<TagDto> tags = new ArrayList<>();
    public List<TypeDto> types = new ArrayList<>();
    public List<WordDto> words = new ArrayList<>();
}

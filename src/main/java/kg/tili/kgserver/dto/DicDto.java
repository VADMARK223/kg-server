package kg.tili.kgserver.dto;

import kg.tili.kgserver.entity.Tag;
import kg.tili.kgserver.entity.Type;
import kg.tili.kgserver.entity.Word;
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
    private List<Tag> tags = new ArrayList<>();
    private List<Type> types = new ArrayList<>();
    private List<Word> words = new ArrayList<>();
}

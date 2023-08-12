package kg.tili.kgserver.dto;

import kg.tili.kgserver.entity.Tag;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Markitanov Vadim
 * @since 05.04.2023
 */
@Data
@Builder
public class WordDto implements Serializable {
    private Long id;
    private Long type;
    private String ru;
    private String kg;
    private Set<Tag> tags;
}

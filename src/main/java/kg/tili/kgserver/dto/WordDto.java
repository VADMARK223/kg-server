package kg.tili.kgserver.dto;

import kg.tili.kgserver.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Markitanov Vadim
 * @since 05.04.2023
 */
@Data
@AllArgsConstructor
public class WordDto implements Serializable {
    public Long id;
    public Long type;
    public String ru;
    public String kg;
    public Set<Tag> tags;
}

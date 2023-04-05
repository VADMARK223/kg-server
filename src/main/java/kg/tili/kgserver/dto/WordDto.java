package kg.tili.kgserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Markitanov Vadim
 * @since 05.04.2023
 */
@Data
@AllArgsConstructor
public class WordDto implements Serializable {
    public String id;
    public Long type;
    public String ru;
    public String kg;
}

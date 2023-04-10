package kg.tili.kgserver.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

/**
 * Сущность категории
 *
 * @author Markitanov Vadim
 * @since 10.04.2023
 */
@Data
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "label")
    private String label;

    @Transient
    @ManyToMany(mappedBy = "tags")
    private Set<Word> words;
}

package kg.tili.kgserver.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Markitanov Vadim
 * @since 05.04.2023
 */
@Setter
@Getter
@Entity
@Accessors(fluent = true)
@Table(name = "words")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ru")
    private String ru;

    @Column(name = "kg")
    private String kg;

    @ManyToOne(fetch = FetchType.LAZY)
    private Type type;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Tag> tags = new HashSet<>();
}

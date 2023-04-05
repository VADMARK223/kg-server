package kg.tili.kgserver.entity;

import jakarta.persistence.*;

/**
 * @author Markitanov Vadim
 * @since 05.04.2023
 */
@Entity
@Table(name = "words")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "type")
    public Long type;

    @Column(name = "ru")
    public String ru;

    @Column(name = "kg")
    public String kg;
}

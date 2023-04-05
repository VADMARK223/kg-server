package kg.tili.kgserver.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author Markitanov Vadim
 * @since 05.04.2023
 */
@Data
@Entity
@Table(name = "words")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "ru")
    public String ru;

    @Column(name = "kg")
    public String kg;

    @ManyToOne(fetch = FetchType.LAZY)
    public Type type;
}

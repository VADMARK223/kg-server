package kg.tili.kgserver.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Сущность категории
 *
 * @author Markitanov Vadim
 * @since 10.04.2023
 */
@Getter
@Setter
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long value;

    @Column(name = "label")
    private String label;

    @Column(name = "color")
    private String color;

    @Column(name = "date_created")
    private Timestamp dateCreated;
}

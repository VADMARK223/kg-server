package kg.tili.kgserver.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author Markitanov Vadim
 * @since 05.04.2023
 */
@Entity
@Table(name = "types")
public class Type {
    @Id
    public Long id;

    @Column(name = "label")
    public String label;
}

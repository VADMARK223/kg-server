package kg.tili.kgserver.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Markitanov Vadim
 * @since 05.04.2023
 */
@Setter
@Getter
@Entity
@Table(name = "types")
public class Type {
    @Id
    private Long id;

    @Column(name = "label")
    private String label;
}

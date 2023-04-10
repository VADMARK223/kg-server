package kg.tili.kgserver.repository;

import kg.tili.kgserver.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Markitanov Vadim
 * @since 10.04.2023
 */
@Repository
public interface TagRepo extends JpaRepository<Tag, Long> {
}

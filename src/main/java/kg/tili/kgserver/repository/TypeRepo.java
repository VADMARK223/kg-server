package kg.tili.kgserver.repository;

import kg.tili.kgserver.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Markitanov Vadim
 * @since 05.04.2023
 */
@Repository
public interface TypeRepo extends JpaRepository<Type, Long> {
}

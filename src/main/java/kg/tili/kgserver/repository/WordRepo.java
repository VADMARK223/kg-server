package kg.tili.kgserver.repository;

import kg.tili.kgserver.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Markitanov Vadim
 * @since 05.04.2023
 */
@Repository
public interface WordRepo extends JpaRepository<Word, Long> {
}

package kg.tili.kgserver.repository;

import kg.tili.kgserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Markitanov Vadim
 * @since 07.04.2023
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

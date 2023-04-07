package kg.tili.kgserver.repository;

import kg.tili.kgserver.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Markitanov Vadim
 * @since 07.04.2023
 */
@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
}

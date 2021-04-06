package pl.projectorc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.projectorc.entities.Role;
import pl.projectorc.entities.User;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(String username);
}

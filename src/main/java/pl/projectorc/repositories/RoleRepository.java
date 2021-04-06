package pl.projectorc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.projectorc.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

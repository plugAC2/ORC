package pl.projectorc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.projectorc.entities.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}

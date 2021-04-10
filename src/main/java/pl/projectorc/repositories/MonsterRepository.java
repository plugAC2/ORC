package pl.projectorc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.projectorc.entities.Monster;

public interface MonsterRepository extends JpaRepository<Monster, Long> {
}

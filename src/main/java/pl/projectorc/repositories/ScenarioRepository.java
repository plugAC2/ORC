package pl.projectorc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.projectorc.entities.Scenario;

public interface ScenarioRepository extends JpaRepository<Scenario, Long> {
}

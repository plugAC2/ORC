package pl.projectorc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.projectorc.entities.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long> {

}

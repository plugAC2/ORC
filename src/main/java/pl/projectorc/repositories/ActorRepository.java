package pl.projectorc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.projectorc.entities.Actor;

import javax.transaction.Transactional;
import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {

    @Query(nativeQuery = true, value = "select * from actor left join users_actors ua on actor.id = ua.actors_id\n" +
            "where general = true or user_id = :id")
    List<Actor> getActorByGeneralAndUser(@Param("id")Long id);

    @Query(nativeQuery = true, value = "select * from actor join users_actors ua on actor.id = ua.actors_id where user_id = :userId and actors_id = :actorId")
    List<Long> getActorByActorIdAndUserId(@Param("userId") Long userId, @Param("actorId") Long actorId);

    @Transactional
    @Modifying
    @Query(value = "delete from users_actors where actors_id = ?1", nativeQuery = true)
    void deleteUserActorKey(Long actorId);
}

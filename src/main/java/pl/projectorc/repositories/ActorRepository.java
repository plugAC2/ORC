package pl.projectorc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.projectorc.entities.Actor;

import javax.transaction.Transactional;
import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {

    @Query(nativeQuery = true, value = "select * from actor left join users_actors ua on actor.id = ua.actor_id\n" +
            "where general = true or user_id = :id")
    List<Actor> getActorByGeneralAndUser(@Param("id")Long id);

    @Query(nativeQuery = true, value = "select * from actor join users_actors ua on actor.id = ua.actor_id where user_id = :userId and actor_id = :actorId")
    List<Long> getActorByActorIdAndUserId(@Param("userId") Long userId, @Param("actorId") Long actorId);

    @Transactional
    @Modifying
    @Query(value = "delete from users_actors where actor_id = ?1", nativeQuery = true)
    void deleteUserActorKey(Long actorId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO users_actors (user_id, actor_id) VALUES (?1, ?2)", nativeQuery = true)
    void linkUserActor(Long userId, Long actorId);
}

package pl.projectorc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.projectorc.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("SELECT u.password FROM User u WHERE u.username LIKE ?1")
    String getUserPassword(String username);

    @Query("SELECT u.id FROM User u WHERE u.username LIKE ?1")
    Long getUserId(String username);

}

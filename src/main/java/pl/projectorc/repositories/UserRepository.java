package pl.projectorc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.projectorc.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUsername(String username);

    @Query("SELECT u.password FROM User u WHERE u.username LIKE ?1")
    public String getUserPassword(String username);
}

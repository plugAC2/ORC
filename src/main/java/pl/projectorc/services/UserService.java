package pl.projectorc.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.projectorc.entities.User;
import pl.projectorc.repositories.UserRepository;
import java.util.*;


@RequiredArgsConstructor
@Service
public class UserService implements CrudService<User>, UserDetailsService {


//    Sec
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User name " + username + "not found"));
    }

//
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void newRecord(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> showRecordById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void changeRecord(User user) {

    }

    @Override
    public void deleteRecordById(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> showRecordByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean checkIfUsernameExist(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent();
    }

    public boolean comparePasswords(String username, String password) {
        return userRepository.getUserPassword(username).equals(password);
    }

}

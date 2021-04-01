package pl.projectorc.services;

import org.springframework.stereotype.Service;
import pl.projectorc.entities.User;
import pl.projectorc.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements CrudService<User> {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

    public boolean checkIfUsernameExist(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent();
    }

    public boolean comparePasswords(String username, String password) {
        return userRepository.getUserPassword(username).equals(password);
    }
}

package pl.projectorc.services;


import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.projectorc.entities.User;
import pl.projectorc.factories.UserEntityModelFactory;
import pl.projectorc.models.UserModel;
import pl.projectorc.repositories.UserRepository;
import java.util.*;


@RequiredArgsConstructor
@Service
public class UserService implements CrudService<User, UserModel>, UserDetailsService {

    private final UserRepository userRepository;
    private final UserEntityModelFactory factory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User name " + username + "not found"));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<UserModel> getAllModel() {
        try {
            throw new NotYetImplementedException();
        } catch (NotYetImplementedException e){
            System.err.println("Method not yet implemented");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void newRecord(UserModel userModel) {
        userRepository.save(factory.createEntityFromModel(userModel));
    }

    @Override
    public void newRecordDirect(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> getRecordById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void changeRecord(Long id, UserModel userModel) {
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

}

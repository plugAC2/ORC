package pl.projectorc.services;


import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.projectorc.entities.User;
import pl.projectorc.models.UserModel;
import pl.projectorc.repositories.RoleRepository;
import pl.projectorc.repositories.UserRepository;
import java.util.*;


@RequiredArgsConstructor
@Service
public class UserService implements CrudService<User, UserModel>, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

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
        userRepository.save(setEntityFromModel(userModel));
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

    @Override
    public User setEntityFromModel(UserModel userModel) {
        String address = userModel.getStreet() + ", " + userModel.getCity() + ", " + userModel.getRegion() + ", " + userModel.getZip() + ", " + userModel.getCountry();
        return User.builder()
                .username(userModel.getUsername())
                .password(passwordEncoder.encode(userModel.getPassword()))
                .email(userModel.getEmail())
                .firstName(userModel.getFirstName())
                .secondName(userModel.getSecondName())
                .address(address)
                .role(roleRepository.findByRoleName("USER").orElseThrow())
                .build();
    }

    @Override
    public UserModel setModelFromEntity(User user) {
        try {
            throw new NotYetImplementedException();
        } catch (NotYetImplementedException e){
            System.err.println("Method not yet implemented");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserModel setModelFromEntityId(Long id) {
        try {
            throw new NotYetImplementedException();
        } catch (NotYetImplementedException e){
            System.err.println("Method not yet implemented");
            e.printStackTrace();
        }
        return null;
    }

    public Optional<User> showRecordByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean checkIfUsernameExist(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent();
    }

}

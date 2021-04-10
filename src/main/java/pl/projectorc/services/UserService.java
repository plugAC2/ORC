package pl.projectorc.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.projectorc.entities.User;
import pl.projectorc.models.UserModel;
import pl.projectorc.repositories.AuthorityRepository;
import pl.projectorc.repositories.RoleRepository;
import pl.projectorc.repositories.UserRepository;
import java.util.*;


@RequiredArgsConstructor
@Service
public class UserService implements CrudService<User>, UserDetailsService {

//    Sec
    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

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
    public Optional<User> getRecordById(Long id) {
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

    public void setUserFromModel(UserModel userModel) {
        String address = userModel.getStreet() + ", " + userModel.getCity() + ", " + userModel.getRegion() + ", " + userModel.getRegion() + ", " + userModel.getCountry();
        newRecord(User.builder()
        .username(userModel.getUsername())
        .password(passwordEncoder.encode(userModel.getPassword()))
        .email(userModel.getEmail())
        .firstName(userModel.getFirstName())
        .secondName(userModel.getSecondName())
        .address(address)
        .role(roleRepository.findByRoleName("USER").orElseThrow())
        .build());
    }
}

package pl.projectorc.factories;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.projectorc.entities.User;
import pl.projectorc.models.UserModel;
import pl.projectorc.repositories.RoleRepository;
import pl.projectorc.repositories.UserRepository;

@Component
@RequiredArgsConstructor
public class UserEntityModelFactory implements EntityModelFactory<User, UserModel> {

    @NonNull
    UserRepository userRepository; //for future

    @NonNull
    RoleRepository roleRepository;

    @NonNull
    PasswordEncoder passwordEncoder;

    @Override
    public User createEntityFromModel(UserModel userModel) {
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
    public UserModel createModelFromEntity(User user) {
        try {
            throw new NotYetImplementedException();
        } catch (NotYetImplementedException e){
            System.err.println("Method not yet implemented");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserModel createModelFromEntityId(Long id) {
        try {
            throw new NotYetImplementedException();
        } catch (NotYetImplementedException e){
            System.err.println("Method not yet implemented");
            e.printStackTrace();
        }
        return null;
    }
}

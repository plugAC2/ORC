package pl.projectorc.factories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.projectorc.entities.Role;
import pl.projectorc.entities.User;
import pl.projectorc.models.UserModel;
import pl.projectorc.repositories.RoleRepository;
import pl.projectorc.repositories.UserRepository;
import pl.projectorc.services.UserService;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserEntityModelFactoryTest {

    @Mock
    UserRepository userRepository;
    @Mock
    RoleRepository roleRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserEntityModelFactory factory;

    @Test
    void shouldCreateEntityFromModel() {

        UserModel userModel = UserModel.builder()
                .username("user")
                .password("password")
                .email("user@email.web")
                .firstName("User")
                .secondName("Resu")
                .street("UserStreet")
                .city("UserCity")
                .region("Userland")
                .zip("123-456")
                .country("Useria")
                .build();

        Mockito.when(passwordEncoder.encode(userModel.getPassword())).thenReturn("encodedPassword");
        Mockito.when(roleRepository.findByRoleName("USER")).thenReturn(Optional.ofNullable(new Role()));

        User user = factory.createEntityFromModel(userModel);

        assertThat(user.getUsername()).isEqualTo(userModel.getUsername());
        assertThat(user.getPassword()).isEqualTo("encodedPassword");
        assertThat(user.getFirstName()).isEqualTo(userModel.getFirstName());
        assertThat(user.getSecondName()).isEqualTo(userModel.getSecondName());
        assertThat(user.getAddress()).isEqualTo(userModel.getStreet() + ", " + userModel.getCity() + ", " + userModel.getRegion() + ", " + userModel.getZip() + ", " + userModel.getCountry());
        assertThat(user.getRoles()).isNotNull();
    }


}

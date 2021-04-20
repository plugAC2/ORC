package pl.projectorc.services;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.projectorc.entities.Role;
import pl.projectorc.entities.User;
import pl.projectorc.models.UserModel;
import pl.projectorc.repositories.RoleRepository;
import pl.projectorc.repositories.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

class UserServiceTest {
    UserRepository userRepository = mock(UserRepository.class);
    RoleRepository roleRepository = mock(RoleRepository.class);
    PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

    @Test
    void shouldCreateEntityFromModel() {

        UserService userService = new UserService(userRepository, roleRepository, passwordEncoder);
        UserModel userModel = UserModel.builder()
                .username("user")
                .password("password")
                .email("user@email.web")
                .firstName("User")
                .secondName("Resu")
                .street("UserStreet")
                .city("UserCity")
                .region("Userland")
                .country("Useria")
                .build();

        Mockito.when(passwordEncoder.encode(userModel.getPassword())).thenReturn("encodedPassword");
        Mockito.when(roleRepository.findByRoleName("USER")).thenReturn(Optional.ofNullable(new Role()));

        User user = userService.setEntityFromModel(userModel);

        assertThat(user.getUsername()).isEqualTo(userModel.getUsername());
        assertThat(user.getPassword()).isEqualTo("encodedPassword");
        assertThat(user.getFirstName()).isEqualTo(userModel.getFirstName());
        assertThat(user.getSecondName()).isEqualTo(userModel.getSecondName());
        assertThat(user.getAddress()).isEqualTo(userModel.getStreet() + ", " + userModel.getCity() + ", " + userModel.getRegion() + ", " + userModel.getRegion() + ", " + userModel.getCountry());
        assertThat(user.getRoles()).isNotNull();
    }

    @Test
    void shouldReturnTrueIfUsernameExists() {

        UserService userService = new UserService(userRepository, roleRepository, passwordEncoder);

        User user = new User();
        user.setUsername("user");

        Mockito.when(userRepository.findByUsername("user")).thenReturn(Optional.ofNullable(user));

        boolean userExistence = userService.checkIfUsernameExist("user");

        assertThat(userExistence).isTrue();
    }

    @Test
    void shouldReturnFalseIfUsernameNotExists() {

        UserService userService = new UserService(userRepository, roleRepository, passwordEncoder);

        User user = new User();
        user.setUsername("user");

        User userTwo = null;

        Mockito.when(userRepository.findByUsername("userTwo")).thenReturn(Optional.ofNullable(userTwo));

        boolean userExistence = userService.checkIfUsernameExist("user");

        assertThat(userExistence).isFalse();
    }
}
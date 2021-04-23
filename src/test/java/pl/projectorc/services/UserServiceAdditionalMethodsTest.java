package pl.projectorc.services;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.projectorc.entities.Role;
import pl.projectorc.entities.User;
import pl.projectorc.factories.UserEntityModelFactory;
import pl.projectorc.models.UserModel;
import pl.projectorc.repositories.RoleRepository;
import pl.projectorc.repositories.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

class UserServiceAdditionalMethodsTest {
    UserRepository userRepository = mock(UserRepository.class);
    RoleRepository roleRepository = mock(RoleRepository.class);
    PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    UserEntityModelFactory factory = mock(UserEntityModelFactory.class);

    @Test
    void shouldReturnTrueIfUsernameExists() {

        UserService userService = new UserService(userRepository, factory);

        User user = new User();
        user.setUsername("user");

        Mockito.when(userRepository.findByUsername("user")).thenReturn(Optional.ofNullable(user));

        boolean userExistence = userService.checkIfUsernameExist("user");

        assertThat(userExistence).isTrue();
    }

    @Test
    void shouldReturnFalseIfUsernameNotExists() {

        UserService userService = new UserService(userRepository, factory);

        User user = new User();
        user.setUsername("user");

        User userTwo = null;

        Mockito.when(userRepository.findByUsername("userTwo")).thenReturn(Optional.ofNullable(userTwo));

        boolean userExistence = userService.checkIfUsernameExist("user");

        assertThat(userExistence).isFalse();
    }
}
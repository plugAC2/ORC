package pl.projectorc.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.projectorc.entities.Actor;
import pl.projectorc.entities.Role;
import pl.projectorc.entities.User;
import pl.projectorc.repositories.UserRepository;
import pl.projectorc.security.UserSecurityUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceCrudTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserSecurityUtil userSecurityUtil;

    @InjectMocks
    UserService userService;

    @Captor
    ArgumentCaptor<User> userCaptor;

    @Test
    void shouldSaveUser() {
//        given
        User user = User.builder()
                .id(999L)
                .username("user")
                .password("password")
                .email("user@email.web")
                .firstName("User")
                .secondName("Resu")
                .address("User-Alley, Usergrad, Useria, 4237, Userland")
                .actor(new Actor(1L, "Xzar", false))
                .role(new Role())
                .build();

        userService.newRecordDirect(user);
//        then
        verify(userRepository).save(userCaptor.capture());

        User capturedValue = userCaptor.getValue();

        assertThat(capturedValue.getId()).isEqualTo(999L);
        assertThat(capturedValue.getUsername()).isEqualTo("user");
        assertThat(capturedValue.getPassword()).isEqualTo("password");
        assertThat(capturedValue.getEmail()).isEqualTo("user@email.web");
        assertThat(capturedValue.getFirstName()).isEqualTo("User");
        assertThat(capturedValue.getSecondName()).isEqualTo("Resu");
        assertThat(capturedValue.getAddress()).isEqualTo("User-Alley, Usergrad, Useria, 4237, Userland");
        assertThat(capturedValue.getActors()).hasAtLeastOneElementOfType(Actor.class);
        assertThat(capturedValue.getRoles()).hasAtLeastOneElementOfType(Role.class);
    }

    @Test
    void shouldDeleteById() {
//        given
        User user = User.builder()
                .id(999L)
                .username("user")
                .password("password")
                .email("user@email.web")
                .firstName("User")
                .secondName("Resu")
                .address("User-Alley, Usergrad, Useria, 4237, Userland")
                .actor(new Actor(1L, "Xzar", false))
                .role(new Role())
                .build();
//        when
        userService.deleteRecordById(999L);
//        then
        verify(userRepository).deleteById(eq(user.getId()));
    }
}

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

        verify(userRepository).save(userCaptor.capture());

        assertThat(userCaptor.getValue().getId()).isEqualTo(999L);
        assertThat(userCaptor.getValue().getUsername()).isEqualTo("user");
        assertThat(userCaptor.getValue().getPassword()).isEqualTo("password");
        assertThat(userCaptor.getValue().getEmail()).isEqualTo("user@email.web");
        assertThat(userCaptor.getValue().getFirstName()).isEqualTo("User");
        assertThat(userCaptor.getValue().getSecondName()).isEqualTo("Resu");
        assertThat(userCaptor.getValue().getAddress()).isEqualTo("User-Alley, Usergrad, Useria, 4237, Userland");
        assertThat(userCaptor.getValue().getActors()).hasAtLeastOneElementOfType(Actor.class);
        assertThat(userCaptor.getValue().getRoles()).hasAtLeastOneElementOfType(Role.class);
    }

    @Test
    void shouldDeleteById() {
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
        
        userService.deleteRecordById(999L);
        
        verify(userRepository).deleteById(eq(user.getId()));
    }
}

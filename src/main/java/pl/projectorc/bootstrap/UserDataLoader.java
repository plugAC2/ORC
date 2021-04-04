package pl.projectorc.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.projectorc.entities.Authority;
import pl.projectorc.entities.User;
import pl.projectorc.repositories.AuthorityRepository;
import pl.projectorc.repositories.UserRepository;

@RequiredArgsConstructor
@Component
public class UserDataLoader implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        if(authorityRepository.count() == 0) {
            loadSecurityData();
        }
    }

    private void loadSecurityData() {
        Authority admin = authorityRepository.save(Authority.builder().role("ADMIN").build());
        Authority user = authorityRepository.save(Authority.builder().role("USER").build());
        userRepository.save(User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .email("admin@admin.pl")
                .firstName("Admin")
                .secondName("Admin")
                .address("Server")
                .authority(admin)
                .build());

        userRepository.save(User.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .email("user@user.pl")
                .firstName("User")
                .secondName("User")
                .address("Computer")
                .authority(user)
                .build());
    }
}

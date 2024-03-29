package pl.projectorc.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.projectorc.entities.Authority;
import pl.projectorc.entities.Role;
import pl.projectorc.entities.User;
import pl.projectorc.repositories.AuthorityRepository;
import pl.projectorc.repositories.RoleRepository;
import pl.projectorc.repositories.UserRepository;

import java.util.Arrays;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class UserDataLoader implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        if(authorityRepository.count() == 0) {
            loadSecurityData();
        }
    }

    private void loadSecurityData() {
        //CRUD scenario
        Authority createScenario = authorityRepository.save(Authority.builder().permission("scenario.create").build());
        Authority updateScenario = authorityRepository.save(Authority.builder().permission("scenario.update").build());
        Authority readScenario = authorityRepository.save(Authority.builder().permission("scenario.read").build());
        Authority deleteScenario = authorityRepository.save(Authority.builder().permission("scenario.delete").build());
        // CRUD character
        Authority createCharacter = authorityRepository.save(Authority.builder().permission("character.create").build());
        Authority updateCharacter = authorityRepository.save(Authority.builder().permission("character.update").build());
        Authority readCharacter = authorityRepository.save(Authority.builder().permission("character.read").build());
        Authority deleteCharacter = authorityRepository.save(Authority.builder().permission("character.delete").build());
        //CRUD monster, map, to add


        Role adminRole = roleRepository.save(Role.builder().roleName("ADMIN").build());
        Role userRole = roleRepository.save(Role.builder().roleName("USER").build());

        adminRole.setAuthorities(Set.of(createScenario, updateScenario, readScenario, deleteScenario));
        userRole.setAuthorities(Set.of(createScenario, updateScenario, readScenario, deleteScenario));

        roleRepository.saveAll(Arrays.asList(adminRole, userRole));

        userRepository.save(User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .email("admin@admin.pl")
                .firstName("Admin")
                .secondName("Admin")
                .address("Server")
                .role(adminRole)
                .build());

        userRepository.save(User.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .email("user@user.pl")
                .firstName("User")
                .secondName("User")
                .address("Computer")
                .role(userRole)
                .build());
    }
}

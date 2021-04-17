package pl.projectorc.bootstrap;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.projectorc.entities.Actor;
import pl.projectorc.entities.Authority;
import pl.projectorc.entities.Role;
import pl.projectorc.entities.User;
import pl.projectorc.repositories.ActorRepository;
import pl.projectorc.repositories.AuthorityRepository;
import pl.projectorc.repositories.RoleRepository;
import pl.projectorc.repositories.UserRepository;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserDataLoader implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    //for data
    private final ActorRepository actorRepository;

    @Override
    public void run(String... args) throws Exception {
        if(authorityRepository.count() == 0) {
            loadSecurityData();
            loadData();
        }
    }

    private void loadSecurityData() {
        //CRUD scenario
        Authority createScenario = authorityRepository.save(Authority.builder().permission("scenario.create").build());
        Authority updateScenario = authorityRepository.save(Authority.builder().permission("scenario.update").build());
        Authority readScenario = authorityRepository.save(Authority.builder().permission("scenario.read").build());
        Authority deleteScenario = authorityRepository.save(Authority.builder().permission("scenario.delete").build());
        // CRUD character
        Authority createActor = authorityRepository.save(Authority.builder().permission("actor.create").build());
        Authority updateActor = authorityRepository.save(Authority.builder().permission("actor.update").build());
        Authority readActor = authorityRepository.save(Authority.builder().permission("actor.read").build());
        Authority deleteActor = authorityRepository.save(Authority.builder().permission("actor.delete").build());
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

    private void loadData() {
        actorRepository.save(Actor.builder()
                .name("Diego")
                .general(true)
                .build());
        actorRepository.save(Actor.builder()
                .name("Ingwar")
                .general(true)
                .build());
        actorRepository.save(Actor.builder()
                .name("Xzar")
                .general(true)
                .build());
    }
}

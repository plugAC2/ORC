package pl.projectorc.security;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.projectorc.repositories.ActorRepository;
import pl.projectorc.repositories.UserRepository;

@RequiredArgsConstructor
@Component
public class UserSecurityUtil {

    @NonNull
    private UserRepository repository;
    @NonNull
    private ActorRepository actorRepository;

    public Long userId() {
        String currentUserName = "error";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        return repository.getUserId(currentUserName);
    }

    public void userOwnsCharacter(Long actorId) {
        if(actorRepository.getActorByActorIdAndUserId(userId(), actorId).isEmpty()){
            throw new SecurityException();
        }
    }
}

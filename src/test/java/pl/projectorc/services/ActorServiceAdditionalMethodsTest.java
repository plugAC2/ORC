package pl.projectorc.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import pl.projectorc.entities.Actor;

import pl.projectorc.factories.ActorEntityModelFactory;
import pl.projectorc.repositories.ActorRepository;
import pl.projectorc.security.UserSecurityUtil;

import java.util.Optional;

class ActorServiceAdditionalMethodsTest {

    ActorRepository actorRepositoryMock = mock(ActorRepository.class);
    ActorEntityModelFactory factory = mock(ActorEntityModelFactory.class);
    UserSecurityUtil userSecurityUtilMock  = mock(UserSecurityUtil.class);



    @Test
    void shouldReturnGeneralTrue() {

        ActorService actorService = new ActorService(actorRepositoryMock, factory, userSecurityUtilMock);

        Actor actor = Actor.builder()
                .id(999L)
                .name("Hel")
                .general(true)
                .build();
        Optional<Actor> actorOptional = Optional.ofNullable(actor);

       when(actorService.getRecordById(999L)).thenReturn(actorOptional);

        boolean generalTest = actorService.checkIfActorGeneral(999L);

        assertThat(generalTest).isTrue();
    }

    @Test
    void shouldReturnGeneralFalse() {

        ActorService actorService = new ActorService(actorRepositoryMock, factory, userSecurityUtilMock);

        Actor actor = Actor.builder()
                .id(999L)
                .name("Hel")
                .general(false)
                .build();
        Optional<Actor> actorOptional = Optional.ofNullable(actor);

        when(actorService.getRecordById(999L)).thenReturn(actorOptional);

        boolean generalTest = actorService.checkIfActorGeneral(999L);

        assertThat(generalTest).isFalse();
    }
}
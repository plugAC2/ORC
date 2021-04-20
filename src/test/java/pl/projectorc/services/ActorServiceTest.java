package pl.projectorc.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.projectorc.entities.Actor;
import pl.projectorc.models.ActorModel;
import pl.projectorc.repositories.ActorRepository;
import pl.projectorc.security.UserSecurityUtil;

import java.util.Optional;

import static org.mockito.Mockito.mock;

class ActorServiceTest {

    ActorRepository actorRepositoryMock = mock(ActorRepository.class);
    UserSecurityUtil userSecurityUtilMock  = mock(UserSecurityUtil.class);

    @Test
    void shouldCreateEntityFromModel() {

        ActorService actorService = new ActorService(actorRepositoryMock, userSecurityUtilMock);

        ActorModel actorModel = ActorModel.builder()
                .id(999L)
                .name("Ingvar")
                .build();

        Actor actor = actorService.setEntityFromModel(actorModel);

        assertThat(actorModel.getName()).isEqualTo(actor.getName());
        assertThat(actor.getGeneral()).isFalse();
    }

    @Test
    void shouldCreateModelFromEntity() {

        ActorService actorService = new ActorService(actorRepositoryMock, userSecurityUtilMock);

        Actor actor = Actor.builder()
                .id(999L)
                .name("Xzar")
                .general(false)
                .build();

        ActorModel actorModel = actorService.setModelFromEntity(actor);

        assertThat(actorModel.getId()).isEqualTo(actor.getId());
        assertThat(actorModel.getName()).isEqualTo(actor.getName());
    }

    @Test
    void shouldCreateModelFromEntityId() {

        ActorService actorService = new ActorService(actorRepositoryMock, userSecurityUtilMock);

        Actor actor = Actor.builder()
                .id(999L)
                .name("Minsk")
                .general(false)
                .build();
        Optional<Actor> actorOptional = Optional.ofNullable(actor);

        Mockito.when(actorService.getRecordById(999L)).thenReturn(actorOptional);

        ActorModel actorModel = actorService.setModelFromEntityId(999L);

        assertThat(actorModel.getId()).isEqualTo(actor.getId());
        assertThat(actorModel.getName()).isEqualTo(actor.getName());
    }

    @Test
    void shouldReturnGeneralTrue() {

        ActorService actorService = new ActorService(actorRepositoryMock, userSecurityUtilMock);

        Actor actor = Actor.builder()
                .id(999L)
                .name("Hel")
                .general(true)
                .build();
        Optional<Actor> actorOptional = Optional.ofNullable(actor);

        Mockito.when(actorService.getRecordById(999L)).thenReturn(actorOptional);

        boolean generalTest = actorService.checkIfActorGeneral(999L);

        assertThat(generalTest).isTrue();
    }

    @Test
    void shouldReturnGeneralFalse() {

        ActorService actorService = new ActorService(actorRepositoryMock, userSecurityUtilMock);

        Actor actor = Actor.builder()
                .id(999L)
                .name("Hel")
                .general(false)
                .build();
        Optional<Actor> actorOptional = Optional.ofNullable(actor);

        Mockito.when(actorService.getRecordById(999L)).thenReturn(actorOptional);

        boolean generalTest = actorService.checkIfActorGeneral(999L);

        assertThat(generalTest).isFalse();
    }
}
package pl.projectorc.services;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import pl.projectorc.entities.Actor;
import pl.projectorc.models.ActorModel;
import pl.projectorc.repositories.ActorRepository;
import pl.projectorc.security.UserSecurityUtil;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ActorServiceCrudTest {

    @Mock
    ActorRepository actorRepository;

    @Mock
    UserSecurityUtil userSecurityUtil;

    @InjectMocks
    ActorService actorService;

    @Captor
    ArgumentCaptor<Actor> actorCaptor;

    @Test
    void shouldSaveActor() {

        Actor actor = Actor.builder()
                .id(999L)
                .name("Xzar")
                .general(false)
                .build();

        actorService.newRecordDirect(actor);

        verify(actorRepository).save(actorCaptor.capture());

        Actor capturedActor = actorCaptor.getValue();

        assertThat(capturedActor.getId()).isEqualTo(999L);
        assertThat(capturedActor.getName()).isEqualTo("Xzar");
        assertThat(capturedActor.getGeneral()).isFalse();
    }


    @Test
    void shouldChangeActor() {

        Actor actor = Actor.builder()
                .id(999L)
                .name("Xzar")
                .general(false)
                .build();
        Optional<Actor> actorOptional = Optional.ofNullable(actor);

        ActorModel actorModelChanges = ActorModel.builder()
                .id(999L)
                .name("Xzardas")
                .build();


        when(actorService.getRecordById(999L)).thenReturn(actorOptional);

        actorService.changeRecord(999L, actorModelChanges);

        verify(actorRepository).save(actorCaptor.capture());

        assertThat(actorCaptor.getValue().getName()).isEqualTo("Xzardas");
    }

    @Test
    void shouldDeleteById() {
        Actor actor = Actor.builder()
                .id(999L)
                .name("Xzar")
                .general(false)
                .build();

        actorService.deleteRecordById(999L);

        verify(actorRepository).deleteById(eq(actor.getId()));

    }

    @Test
    void shouldDeleteUserActorKeyById() {
        Actor actor = Actor.builder()
                .id(999L)
                .name("Xzar")
                .general(false)
                .build();

        actorService.deleteRecordById(999L);

        verify(actorRepository).deleteUserActorKey(eq(actor.getId()));
    }
}

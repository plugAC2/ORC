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
//        given
        Actor actor = Actor.builder()
                .id(999L)
                .name("Xzar")
                .general(false)
                .build();

        actorService.newRecordDirect(actor);

        verify(actorRepository).save(actorCaptor.capture());
//        when
        Actor capturedActor = actorCaptor.getValue();
//        then
        assertThat(capturedActor.getId()).isEqualTo(999L);
        assertThat(capturedActor.getName()).isEqualTo("Xzar");
        assertThat(capturedActor.getGeneral()).isFalse();
    }


    @Test
    void shouldChangeActor() {
//        given
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
//        when
        actorService.changeRecord(999L, actorModelChanges);
//        then
        verify(actorRepository).save(actorCaptor.capture());

        assertThat(actorCaptor.getValue().getName()).isEqualTo("Xzardas");
    }

    @Test
    void shouldDeleteById() {
//        given
        Actor actor = Actor.builder()
                .id(999L)
                .name("Xzar")
                .general(false)
                .build();
//        when
        actorService.deleteRecordById(999L);
//        then
        verify(actorRepository).deleteById(eq(actor.getId()));

    }

    @Test
    void shouldDeleteUserActorKeyById() {
//        given
        Actor actor = Actor.builder()
                .id(999L)
                .name("Xzar")
                .general(false)
                .build();
//        when
        actorService.deleteRecordById(999L);
//        then
        verify(actorRepository).deleteUserActorKey(eq(actor.getId()));
    }
}

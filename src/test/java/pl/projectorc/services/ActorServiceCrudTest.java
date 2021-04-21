package pl.projectorc.services;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import pl.projectorc.entities.Actor;
import pl.projectorc.repositories.ActorRepository;
import pl.projectorc.security.UserSecurityUtil;

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

    @Captor
    ArgumentCaptor<Actor> actorModelCaptor;

    @Test
    void shouldSaveActor() {

        Actor actor = Actor.builder()
                .id(999L)
                .name("Xzar")
                .general(false)
                .build();

        actorService.newRecordDirect(actor);

        verify(actorRepository).save(actorCaptor.capture());

        assertThat(actorCaptor.getValue().getId()).isEqualTo(999L);
        assertThat(actorCaptor.getValue().getName()).isEqualTo("Xzar");
        assertThat(actorCaptor.getValue().getGeneral()).isFalse();
    }

}

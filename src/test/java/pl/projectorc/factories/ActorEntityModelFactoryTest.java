package pl.projectorc.factories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.projectorc.entities.Actor;
import pl.projectorc.models.ActorModel;
import pl.projectorc.repositories.ActorRepository;
import pl.projectorc.security.UserSecurityUtil;
import pl.projectorc.services.ActorService;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ActorEntityModelFactoryTest {

    @Mock
    ActorRepository actorRepositoryMock;

    UserSecurityUtil userSecurityUtilMock  = mock(UserSecurityUtil.class);

    @InjectMocks
    ActorEntityModelFactory factory;

    @Test
    void shouldCreateEntityFromModel() {

        ActorModel actorModel = ActorModel.builder()
                .id(999L)
                .name("Ingvar")
                .build();

        Actor actor = factory.createEntityFromModel(actorModel);

        assertThat(actorModel.getName()).isEqualTo(actor.getName());
        assertThat(actor.getGeneral()).isFalse();
    }

    @Test
    void shouldCreateModelFromEntity() {

        Actor actor = Actor.builder()
                .id(999L)
                .name("Xzar")
                .general(false)
                .build();

        ActorModel actorModel = factory.createModelFromEntity(actor);

        assertThat(actorModel.getId()).isEqualTo(actor.getId());
        assertThat(actorModel.getName()).isEqualTo(actor.getName());
    }

    @Test
    void shouldCreateModelFromEntityId() {

        ActorService actorService = new ActorService(actorRepositoryMock, factory, userSecurityUtilMock);

        Actor actor = Actor.builder()
                .id(999L)
                .name("Minsk")
                .general(false)
                .build();

        Optional<Actor> actorOptional = Optional.ofNullable(actor);

        when(actorService.getRecordById(999L)).thenReturn(actorOptional);

        ActorModel actorModel = factory.createModelFromEntityId(999L);

        assertThat(actorModel.getId()).isEqualTo(actor.getId());
        assertThat(actorModel.getName()).isEqualTo(actor.getName());
    }

}

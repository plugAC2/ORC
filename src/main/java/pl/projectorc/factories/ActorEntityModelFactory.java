package pl.projectorc.factories;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.projectorc.entities.Actor;
import pl.projectorc.models.ActorModel;
import pl.projectorc.repositories.ActorRepository;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class ActorEntityModelFactory implements EntityModelFactory<Actor, ActorModel> {

    @NonNull
    private final ActorRepository actorRepository;

    @Override
    public Actor createEntityFromModel(ActorModel actorModel) {
        return Actor.builder()
                .name(actorModel.getName())
                .general(false)
                .build();
    }

    @Override
    public ActorModel createModelFromEntity(Actor actor) {

        return ActorModel.builder()
                .id(actor.getId())
                .name(actor.getName())
                .build();
    }

    @Override
    public ActorModel createModelFromEntityId(Long id) {

        Actor actor = actorRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return ActorModel.builder()
                .id(actor.getId())
                .name(actor.getName())
                .build();
    }
}

package pl.projectorc.services;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.projectorc.entities.Actor;
import pl.projectorc.models.ActorModel;
import pl.projectorc.repositories.ActorRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ActorService implements CrudService<Actor>{

    @NonNull
    private ActorRepository actorRepository;

    @Override
    public List<Actor> getAll() {
        return actorRepository.findAll();
    }

    @Override
    public void newRecord(Actor actor) {
        actorRepository.save(actor);
    }

    @Override
    public Optional<Actor> getRecordById(Long id) {
        return actorRepository.findById(id);
    }

    @Override
    public void changeRecord(Actor actor) {
        actorRepository.save(actor);
    }

    @Override
    public void deleteRecordById(Long id) {

    }

    public Actor setActorFromModel(ActorModel actorModel) {
        return Actor.builder()
                .name(actorModel.getName())
                .general(false)
                .build();
    }

    public ActorModel setModelFromActorId(Long id) throws NoSuchElementException {
        Actor actor = getRecordById(id).orElseThrow(NoSuchElementException::new);
        return ActorModel.builder()
                .name(actor.getName())
                .build();
    }
}

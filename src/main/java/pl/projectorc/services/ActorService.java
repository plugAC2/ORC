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
public class ActorService implements CrudService<Actor, ActorModel>{

    @NonNull
    private ActorRepository actorRepository;

    @Override
    public List<Actor> getAll() {
        return actorRepository.findAll();
    }

    @Override
    public void newRecord(ActorModel actorModel) {
        actorRepository.save(setEntityFromModel(actorModel));
    }

    @Override
    public Optional<Actor> getRecordById(Long id) {
        return actorRepository.findById(id);
    }

    @Override
    public void changeRecord(Long id, ActorModel actorModel) {
        ActorModel actorModelToChange = setModelFromEntityId(id);
        System.out.println(actorModel.getName());
        actorModelToChange.setName(actorModel.getName());
        newRecord(actorModelToChange);
    }

    @Override
    public void deleteRecordById(Long id) {

    }

    @Override
    public Actor setEntityFromModel(ActorModel actorModel) {
        return Actor.builder()
                .name(actorModel.getName())
                .general(false)
                .build();
    }

    @Override
    public ActorModel setModelFromEntityId(Long id) {
        Actor actor = getRecordById(id).orElseThrow(NoSuchElementException::new);
        return ActorModel.builder()
                .name(actor.getName())
                .build();
    }

}

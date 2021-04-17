package pl.projectorc.services;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.projectorc.entities.Actor;
import pl.projectorc.models.ActorModel;
import pl.projectorc.repositories.ActorRepository;
import pl.projectorc.security.UserSecurityUtil;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ActorService implements CrudService<Actor, ActorModel> {

    @NonNull
    private ActorRepository actorRepository;
    @NonNull
    private UserSecurityUtil userSecurityUtil;

    @Override
    public List<Actor> getAll() {
        return actorRepository.getActorByGeneralAndUser(userSecurityUtil.userId());
    }

    @Override
    public List<ActorModel> getAllModel() {
        List<Actor> actors = actorRepository.getActorByGeneralAndUser(userSecurityUtil.userId());
        return actors.stream()
                .map(this::setModelFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void newRecord(ActorModel actorModel) {
        Actor savedActor = actorRepository.save(setEntityFromModel(actorModel));
        actorRepository.linkUserActor(userSecurityUtil.userId(), savedActor.getId());
    }

    @Override
    public void newRecordDirect(Actor actor) {
        actorRepository.save(actor);
    }

    @Override
    public Optional<Actor> getRecordById(Long id) {
        return actorRepository.findById(id);
    }

    @Override
    public void changeRecord(Long id, ActorModel actorModel) {
        ActorModel actorModelToChange = setModelFromEntityId(id);
        actorModelToChange.setName(actorModel.getName());
        Actor actorToUpdate = setEntityFromModel(actorModelToChange);
        actorToUpdate.setId(id);
        System.out.println(actorToUpdate.getName());
        newRecordDirect(actorToUpdate);
    }

    @Override
    public void deleteRecordById(Long id) {
        actorRepository.deleteUserActorKey(id);
        actorRepository.deleteById(id);

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

    public ActorModel setModelFromEntity(Actor actor) {
        return ActorModel.builder()
                .id(actor.getId())
                .name(actor.getName())
                .build();
    }

    public boolean checkIfActorGeneral(Long id) {
        Actor actor = getRecordById(id).orElseThrow(NoSuchElementException::new);
        return actor.getGeneral();
    }

}

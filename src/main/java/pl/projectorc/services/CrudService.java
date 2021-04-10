package pl.projectorc.services;

import pl.projectorc.entities.Actor;
import pl.projectorc.models.ActorModel;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, M> {
    List<T> getAll();
    void newRecord(M m);
    void newRecordDirect(T t);
    Optional<T> getRecordById(Long id);
    void changeRecord(Long id, M m);
    void deleteRecordById(Long id);
    T setEntityFromModel(M m);
    M setModelFromEntityId(Long id);

}

package pl.projectorc.services;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, M> {
    List<T> getAll();
    List<M> getAllModel();
    void newRecord(M m);
    void newRecordDirect(T t);
    Optional<T> getRecordById(Long id);
    void changeRecord(Long id, M m);
    void deleteRecordById(Long id);
    T setEntityFromModel(M m);
    M setModelFromEntity(T t);
    M setModelFromEntityId(Long id);

}

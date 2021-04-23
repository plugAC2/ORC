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

}

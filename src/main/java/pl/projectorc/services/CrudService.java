package pl.projectorc.services;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {
    List<T> getAll();
    void newRecord(T t);
    Optional<T> getRecordById(Long id);
    void changeRecord(T t);
    void deleteRecordById(Long id);
}

package ru.ddc.b2bcolab.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {

    T save(T model);

    List<T> findAll();

    Optional<T> findById(ID id);

    int update(T t);

    int deleteById(ID id);

    boolean exists(ID id);
}

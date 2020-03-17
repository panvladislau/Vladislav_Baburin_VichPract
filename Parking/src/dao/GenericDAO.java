package dao;

import java.util.List;

import domain.AbstractEntity;

public interface GenericDAO<T extends AbstractEntity> {

    T create(T entity);

    T read(long id);

    List<T> readAll();

    void update(T entity);

    void delete(T entity);
}

package code.dao;

import code.domain.AbstractEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Optional;

public interface GenericDAO<T extends AbstractEntity> {
    boolean create(T entity);

    Optional<T> read(int id);

    ArrayList<T> readAll();

    boolean update(T entity);

    boolean delete(int id);
}

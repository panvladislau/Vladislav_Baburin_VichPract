package code.dao;

import code.domain.CarEntity;

import java.util.ArrayList;
import java.util.List;

public interface CarDAO extends GenericDAO<CarEntity> {
    ArrayList<CarEntity> findByOwner(String owner);
}

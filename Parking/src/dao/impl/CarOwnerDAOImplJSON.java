package dao.impl;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import dao.CarOwnerDAO;
import domain.CarOwner;

public class CarOwnerDAOImplJSON implements CarOwnerDAO {

    // todo read/write from/to file
    @Override
    public CarOwner create(CarOwner entity) {
        //entity.setId(ThreadLocalRandom.current().nextLong()); // do not use this, just for demo
        return entity;
    }

    @Override
    public CarOwner read(long id) {
        return null;
    }

    @Override
    public List<CarOwner> readAll() {
        return null;
    }

    @Override
    public void update(CarOwner entity) {}

    @Override
    public void delete(CarOwner entity) {}

    @Override
    public CarOwner findByUsername(String username) {
        return null;
    }
}
package dao.impl;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import dao.CarDAO;
import domain.Car;

public class CarDAOImplJSON implements CarDAO {

    // todo read/write from/to file
    @Override
    public Car create(Car entity) {
        entity.setId(ThreadLocalRandom.current().nextLong()); // do not use this, just for demo
        return entity;
    }

    @Override
    public Car read(long id) {
        return null;
    }

    @Override
    public List<Car> readAll() {
        return null;
    }

    @Override
    public void update(Car entity) {}

    @Override
    public void delete(Car entity) {}

    @Override
    public Car findByPlate(String plate) {
        return null;
    }
}

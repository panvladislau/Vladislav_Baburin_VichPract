package dao.impl;

import java.util.List;

import dao.CarOwnerDAO;
import domain.CarOwner;

// todo not needed for now
public class CarOwnerDAOImplDB implements CarOwnerDAO {

    @Override
    public CarOwner findByUsername(String username) {
        return null;
    }

    @Override
    public CarOwner create(CarOwner entity) {
        return null;
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
}
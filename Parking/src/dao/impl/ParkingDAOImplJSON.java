package dao.impl;

import java.util.List;

import dao.ParkingDAO;
import domain.CarOwner;
import domain.Parking;

public class ParkingDAOImplJSON implements ParkingDAO {
    @Override
    public Parking create(Parking entity) {
        return entity;
    }

    @Override
    public Parking read(long id) {
        return null;
    }

    @Override
    public List<Parking> readAll() {
        return null;
    }

    @Override
    public void update(Parking entity) {}

    @Override
    public void delete(Parking entity) {}

    @Override
    public Parking findByParkingName(String parkingName) {
        return null;
    }
}

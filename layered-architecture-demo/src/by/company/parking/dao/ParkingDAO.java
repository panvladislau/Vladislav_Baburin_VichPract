package dao;

import domain.Parking;

public interface ParkingDAO extends GenericDAO<Parking> {

    Parking findByParkingName(string parkingName);
}

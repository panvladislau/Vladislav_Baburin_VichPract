package code.service;

import code.domain.ParkingPlaceEntity;

import java.util.ArrayList;
import java.util.List;

public interface ParkingPlaceService {
    boolean create(ParkingPlaceEntity parkingPlace);
    ParkingPlaceEntity read(int id);
    boolean update(ParkingPlaceEntity parkingPlace);
    boolean delete(int id);
    ArrayList<ParkingPlaceEntity> readAll();
    ArrayList<ParkingPlaceEntity> findByParkingName(String parkingName);

    void listAllParkingPlaces();
    void showParkingPlace(int id);
}

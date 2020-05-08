package code.service;

import code.domain.Parking;

import java.util.ArrayList;
import java.util.List;

public interface ParkingService {
    boolean create(Parking parking);
    Parking read(int id);
    boolean update(Parking parking);
    boolean delete(int id);
    ArrayList<Parking> readAll();

    void listAllParkings();
    void showParking(int id);
}

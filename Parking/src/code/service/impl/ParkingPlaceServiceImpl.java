package code.service.impl;

import code.dao.DAOFactory;
import code.dao.ParkingDAO;
import code.dao.ParkingPlaceDAO;
import code.domain.CarEntity;
import code.domain.Parking;
import code.domain.ParkingPlaceEntity;
import code.exception.ValidationException;
import code.service.ParkingPlaceService;
import code.service.ParkingService;

import java.util.ArrayList;
import java.util.List;

public class ParkingPlaceServiceImpl implements ParkingPlaceService {

    private static final ParkingPlaceDAO dao = DAOFactory.getParkingPlaceDAO();

    @Override
    public boolean create(ParkingPlaceEntity parkingPlace) {
        checkParkingPlace(parkingPlace);
        return dao.create(parkingPlace);
    }

    @Override
    public ParkingPlaceEntity read(int id) {
        return dao.read(id).orElse(null);
    }

    @Override
    public boolean update(ParkingPlaceEntity parkingPlace) {
        checkParkingPlace(parkingPlace);
        return dao.update(parkingPlace);
    }

    @Override
    public boolean delete(int id) {
        return dao.delete(id);
    }

    @Override
    public void listAllParkingPlaces() {
        List<ParkingPlaceEntity> parkingPlaces = dao.readAll();
        for (ParkingPlaceEntity parkingPlace: parkingPlaces) {
            System.out.println(parkingPlace + "\n");
        }
    }

    @Override
    public void showParkingPlace(int id) {
        if (dao.read(id).isEmpty()) {
            System.out.println("No such elements");
        } else {
            System.out.println(dao.read(id).get());
        }
    }

    @Override
    public ArrayList<ParkingPlaceEntity> readAll(){
        return dao.readAll();
    }

    @Override
    public ArrayList<ParkingPlaceEntity> findByParkingName(String parkingName) {
        return dao.findByParkingName(parkingName);
    }

    private void checkParkingPlace(ParkingPlaceEntity parkingPlace) {
        if (parkingPlace == null) {
            throw new ValidationException("Invalid parking place.");
        }

        String parking_name = parkingPlace.getParkingName();
        if (parking_name == null || parking_name.isEmpty()) {
            throw new ValidationException("Parking name is required.");
        }

        String placeNumber = parkingPlace.getPlaceNumber();
        if (placeNumber == null || placeNumber.isEmpty()) {
            throw new ValidationException("Place number is required.");
        }
    }
}
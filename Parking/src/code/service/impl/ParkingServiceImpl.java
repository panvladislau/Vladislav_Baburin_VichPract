package code.service.impl;

import code.dao.DAOFactory;
import code.dao.ParkingDAO;
import code.dao.UserDAO;
import code.domain.Parking;
import code.domain.User;
import code.exception.ValidationException;
import code.service.ParkingService;
import code.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class ParkingServiceImpl implements ParkingService {

    private static final ParkingDAO dao = DAOFactory.getParkingDAO();

    @Override
    public boolean create(Parking parking) {
        checkParking(parking);
        return dao.create(parking);
    }

    @Override
    public Parking read(int id) {
        return dao.read(id).orElse(null);
    }

    @Override
    public boolean update(Parking parking) {
        checkParking(parking);
        return dao.update(parking);
    }

    @Override
    public boolean delete(int id) {
        return dao.delete(id);
    }

    @Override
    public void listAllParkings() {
        List<Parking> parkings = dao.readAll();
        for (int i=0; i<parkings.size(); i++) {
            System.out.println(parkings.get(i).toString() + "\n");
        }
    }

    @Override
    public void showParking(int id) {
        if (dao.read(id).isEmpty()) {
            System.out.println("No such elements");
        } else {
            System.out.println(dao.read(id).get());
        }
    }

    @Override
    public ArrayList<Parking> readAll(){
        return dao.readAll();
    }

    private void checkParking(Parking parking) {
        if (parking == null) {
            throw new ValidationException("Invalid parking.");
        }

        String parking_name = parking.getName();
        if (parking_name == null || parking_name.isEmpty()) {
            throw new ValidationException("Parking name is required.");
        }
    }
}
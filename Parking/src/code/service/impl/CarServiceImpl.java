package code.service.impl;

import code.dao.CarDAO;
import code.dao.DAOFactory;
import code.dao.UserDAO;
import code.domain.CarEntity;
import code.domain.User;
import code.exception.ValidationException;
import code.service.CarService;
import code.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class CarServiceImpl implements CarService {

    private static final CarDAO dao = DAOFactory.getCarDAO();

    @Override
    public boolean create(CarEntity car) {
        checkCar(car);
        return dao.create(car);
    }

    @Override
    public CarEntity read(int id) {
        return dao.read(id).orElse(null);
    }

    @Override
    public boolean update(CarEntity car) {
        checkCar(car);
        return dao.update(car);
    }

    @Override
    public boolean delete(int id) {
        return dao.delete(id);
    }

    @Override
    public void listAllCars() {
        List<CarEntity> cars = dao.readAll();
        for (CarEntity car: cars) {
            System.out.println(car + "\n");
        }
    }

    @Override
    public void showCar(int id) {
        if (dao.read(id).isEmpty()) {
            System.out.println("No such elements");
        } else {
            System.out.println(dao.read(id).get());
        }
    }

    @Override
    public ArrayList<CarEntity> readAll(){
        return dao.readAll();
    }


    @Override
    public ArrayList<CarEntity> findByOwner(String owner) {
        return dao.findByOwner(owner);
    }

    private void checkCar(CarEntity car) {
        if (car == null) {
            throw new ValidationException("Invalid car.");
        }

        String carNumber = car.getCarNumber();
        if (carNumber == null || carNumber.isEmpty()) {
            throw new ValidationException("Car number is required.");
        }

        String owner = car.getOwner();
        if (owner == null || owner.isEmpty()) {
            throw new ValidationException("Owner is required.");
        }
    }
}
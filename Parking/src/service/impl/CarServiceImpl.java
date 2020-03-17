package service.impl;

import java.util.Optional;

import dao.CarDAO;
import dao.DAOFactory;
import domain.Car;
import exception.ValidationException;
import service.CarService;

public class CarServiceImpl implements CarService {

    private static final CarDAO dao = DAOFactory.getCarDAO();

    @Override
    public Car create(Car car) {
        // todo validations can be moved to Validator class
        if (car == null) {
            throw new ValidationException("Invalid car");
        }

        String plate = car.getPlate();
        if (plate == null || plate.isEmpty()) {
            throw new ValidationException("Plate is required");
        }

        if (plate.length() < 7) {
            // todo  regexp can be added to check plate format
            throw new ValidationException("Plate is in wrong format");
        }

        // todo check other fields
        return dao.create(car);
    }

    @Override
    public Optional<Car> findByPlate(String plate) {
        if (plate == null || plate.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(dao.findByPlate(plate));
    }
}

package service.impl;

import java.util.Optional;

import dao.CarOwnerDAO;
import dao.DAOFactory;
import domain.CarOwner;
import exception.ValidationException;
import service.CarOwnerService;

public class CarOwnerServiceImpl implements CarOwnerService {

    private static final CarOwnerDAO dao = DAOFactory.getCarOwnerDAO();

    @Override
    public CarOwner create(CarOwner car) {
        // todo validations can be moved to Validator class
        if (carOwner == null) {
            throw new ValidationException("Invalid car Owner");
        }

        String username = carOwner.getUsername();
        if (username == null || username.isEmpty()) {
            throw new ValidationException("Username is required");
        }

        String password = carOwner.getPassword();
        if (passeord == null || password.isEmpty()) {
            throw new ValidationException("Password is required");
        }

        String carOwnerName = carOwner.getCarOwnerName ();
        if (carOwnerName  == null || carOwnerName.isEmpty()) {
            throw new ValidationException("Car owner name is required");
        }

        String carOwnerSecondname = carOwner.getcarOwnerSecondname ();
        if (carOwnerSecondname  == null || carOwnerSecondname.isEmpty()) {
            throw new ValidationException("Car owner  second name is required");
        }

        // todo check other fields
        return dao.create(car);
    }

    @Override
    public Optional<Car> findByUsername(String username) {
        if (username == null || username.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(dao.findByUsername(username));
    }
}
package service;

import java.util.Optional;

import domain.Car;

public interface CarService {

    Car create(Car car);

    Optional<Car> findByPlate(String plate);
}

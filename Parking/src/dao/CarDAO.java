package dao;

import domain.Car;
import domain.CarOwner;

public interface CarDAO extends GenericDAO<Car> {

    Car findByPlate(String plate);
}

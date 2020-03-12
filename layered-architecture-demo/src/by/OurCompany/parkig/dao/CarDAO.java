package dao;

import domain.Car;

public interface CarDAO extends GenericDAO<Car> {

  Car findByPlate(String plate);
  Car findByOwner(CarOwner owner);
}

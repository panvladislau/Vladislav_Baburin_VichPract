package dao.impl;

import java.util.List;

import dao.CarDAO;
import domain.Car;

// todo not needed for now
public class CarDAOImplDB implements CarDAO {

  @Override
  public Car findByPlate(String plate) {
    return null;
  }

  @Override
  public Car create(Car entity) {
    return null;
  }

  @Override
  public Car read(long id) {
    return null;
  }

  @Override
  public List<Car> readAll() {
    return null;
  }

  @Override
  public void update(Car entity) {}

  @Override
  public void delete(Car entity) {}
}

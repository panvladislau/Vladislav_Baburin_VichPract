package code.service;

import code.domain.CarEntity;

import java.util.ArrayList;
import java.util.List;

public interface CarService {
    boolean create(CarEntity car);
    CarEntity read(int id);
    boolean update(CarEntity car);
    boolean delete(int id);
    ArrayList<CarEntity> readAll();
    ArrayList<CarEntity> findByOwner(String owner);

    void listAllCars();
    void showCar(int id);
}
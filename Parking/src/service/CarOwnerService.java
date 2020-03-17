package service;

import java.util.Optional;

import domain.CarOwner;

public interface CarOwnerService {

    CarOwner create(CarOwner carOwner);

    Optional<CarOwner> findByUsername(String username);
}
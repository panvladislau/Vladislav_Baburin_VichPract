package dao;

import domain.CarOwner;

public interface CarOwnerDAO extends GenericDAO<CarOwner> {

    CarOwner findByUsername(String username);
}
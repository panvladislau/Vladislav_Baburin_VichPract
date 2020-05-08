package code.dao;

import code.domain.ParkingPlaceEntity;

import java.util.ArrayList;
import java.util.List;

public interface ParkingPlaceDAO extends GenericDAO<ParkingPlaceEntity>{
    ArrayList<ParkingPlaceEntity> findByParkingName(String parkingName);
}

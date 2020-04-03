package code.service;

import code.dao.ParkingEntity;
import code.dao.ParkingPlaceEntity;
import code.domain.Parking;

public class ParkingMapper {

    public Parking mapToParking(ParkingEntity parkingEntity) throws Exception {
        String name = parkingEntity.getName();
        ParkingPlaceEntity[] parkingPlaces = parkingEntity.getParkingPlaces();
        return new Parking(name, parkingPlaces);
    }
}

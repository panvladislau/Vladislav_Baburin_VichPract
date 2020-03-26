package code.service;

import code.dao.ParkingPlaceEntity;
import code.domain.ParkingPlace;

public class ParkingPlaceMapper {

    public ParkingPlace mapToParkingPlace(ParkingPlaceEntity parkingPlaceEntity) throws Exception {
        String parkingName = parkingPlaceEntity.getParkingName();
        String placeNumber = parkingPlaceEntity.getPlaceNumber();
        String locatedCarNumber = parkingPlaceEntity.getLocatedCarNumber();
        String id = parkingPlaceEntity.getId();
        String currentUser = parkingPlaceEntity.getCurrentUser();
        return new ParkingPlace(parkingName, placeNumber, locatedCarNumber, id, currentUser);
    }
}

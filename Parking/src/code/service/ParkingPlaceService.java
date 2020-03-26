package code.service;

import code.dao.ParkingPlaceEntity;
import code.dao.ParkingPlaceRepository;
import org.json.simple.parser.ParseException;

import code.domain.User;
import code.domain.ParkingPlace;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class ParkingPlaceService {
    ParkingPlaceRepository parkingPlaceRepository = new ParkingPlaceRepository();
    ParkingPlaceMapper mapper = new ParkingPlaceMapper();

    public ParkingPlaceService() throws ParseException, java.text.ParseException, IOException {
    }

    public ArrayList<String> getParkingPlaceStrings() throws Exception {
        ArrayList<ParkingPlace> parkingPlaces = getParkingPlace();
        ArrayList<String> parkingPlaceStrings = new ArrayList<>();

        for (ParkingPlace parkingPlace: parkingPlaces) {
            parkingPlaceStrings.add(parkingPlace.toString());
        }

        return parkingPlaceStrings;
    }

    public ArrayList<ParkingPlace> getParkingPlace() throws Exception{
        Hashtable<String, ParkingPlaceEntity> parkingPlacesInFile = parkingPlaceRepository.getAll();
        ArrayList<ParkingPlace> parkingPlaces = new ArrayList<>();

        for (String key : parkingPlacesInFile.keySet()) {
            ParkingPlaceEntity parkingPlaceEntity = parkingPlacesInFile.get(key);
            ParkingPlace parkingPlace = mapper.mapToParkingPlace(parkingPlaceEntity);
            parkingPlaces.add(parkingPlace);
        }

        return parkingPlaces;
    }

    public void setCar(ParkingPlace parkingPlace, String carNumber, User user) throws IOException {
        if (parkingPlace.getLocatedCarNumber().equals("")) {
            ParkingPlaceEntity entity = new ParkingPlaceEntity(parkingPlace.getParkingName(), parkingPlace.getPlaceNumber(),
                    carNumber, parkingPlace.getId(), user.getUsername());
            parkingPlaceRepository.update(entity, entity.getId());
            System.out.print("Сar was successfully placed!\n");
       }
        else System.out.print("Another car at this place!!! Please, choose another parking place\n");
    }

    public void cleanPlace(ParkingPlace parkingPlace, User user) throws IOException {
        if (parkingPlace.getCurrentUser().equals(user.getUsername())){
            ParkingPlaceEntity entity = new ParkingPlaceEntity(parkingPlace.getParkingName(), parkingPlace.getPlaceNumber(),
                    "", parkingPlace.getId(), "");
            parkingPlaceRepository.update(entity, entity.getId());
            System.out.print("Сar was successfully removed!\n");
        }
        else System.out.print("There is no your car on this place\n");
    }
}

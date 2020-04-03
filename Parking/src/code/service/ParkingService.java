package code.service;

import code.dao.CarEntity;
import code.dao.ParkingEntity;
import code.dao.ParkingRepository;
import code.domain.Parking;
import code.domain.User;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class ParkingService {
    ParkingRepository parkingRepository = new ParkingRepository();
    ParkingMapper mapper = new ParkingMapper();

    public ParkingService() throws ParseException, java.text.ParseException, IOException {
    }

    public ArrayList<String> getParkingStrings() throws Exception {
        ArrayList<Parking> parkingList = getParking();
        ArrayList<String> parkingStrings = new ArrayList<>();

        for (Parking parking : parkingList) {
            parkingStrings.add(parking.toString());
        }

        return parkingStrings;
    }

    public ArrayList<Parking> getParking() throws Exception {
        Hashtable<String, ParkingEntity> parkingInFile = parkingRepository.getAll();
        ArrayList<Parking> parkingList = new ArrayList<>();

        for (String key : parkingInFile.keySet()) {
            ParkingEntity parkingEntity = parkingInFile.get(key);
            Parking parking = mapper.mapToParking(parkingEntity);
            parkingList.add(parking);
        }

        return parkingList;
    }

    public User SetCar(User user, String carNumber, String id) throws Exception {
        ParkingService parkingService = new ParkingService();
        ArrayList<Parking> parkingList = parkingService.getParking();
        for (int k = 0; k < parkingList.size(); k++) {
            for (int j=0; j<parkingList.get(k).getParkingPlaces().length; j++){
                if (id.equals(parkingList.get(k).getParkingPlaces()[j].getId())) {
                    if (parkingList.get(k).getParkingPlaces()[j].getSettedCar().getCarNumber().equals("")) {

                        CarEntity car = new CarEntity(carNumber, user.getUsername());
                        ParkingEntity entity = new ParkingEntity(parkingList.get(k).getName(), parkingList.get(k).getParkingPlaces());
                        entity.getParkingPlaces()[j].setSettedCar(car);
                        parkingRepository.update(entity, parkingList.get(k).getName());
                        System.out.print("Сar was successfully placed!\n\n");
                        return user;
                    }
                    else {
                        System.out.print("Another car at this place!\nPlease, choose another place\n\n");
                        return user;
                    }
                }
            }
        }
        System.out.print("Wrong place id!\nPlease, try again\n\n");
        return user;
    }

    public User RemoveCar(User user, String carNumber) throws Exception {
        ParkingService parkingService = new ParkingService();
        ArrayList<Parking> parkingList = parkingService.getParking();
        for (int k = 0; k < parkingList.size(); k++) {
            for (int j=0; j<parkingList.get(k).getParkingPlaces().length; j++){
                    if (parkingList.get(k).getParkingPlaces()[j].getSettedCar().getCarNumber().equals(carNumber)) {

                        CarEntity car = new CarEntity("", "");
                        ParkingEntity entity = new ParkingEntity(parkingList.get(k).getName(), parkingList.get(k).getParkingPlaces());
                        entity.getParkingPlaces()[j].setSettedCar(car);
                        parkingRepository.update(entity, parkingList.get(k).getName());
                        System.out.print("Сar was successfully removed!\n\n");
                        return user;
                    }
            }
        }
        return user;
    }
}
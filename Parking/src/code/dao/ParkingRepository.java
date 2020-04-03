package code.dao;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Optional;

public class ParkingRepository implements GenericDAO<String, ParkingEntity> {
    Hashtable<String, ParkingEntity> parkingTable = new Hashtable<String, ParkingEntity>();

    public ParkingRepository() throws IOException, ParseException {
        File file = Paths.get(System.getProperty("user.dir"), "resources/Parking.json").toFile();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(file.toString()));
        JSONArray jsonArray = (JSONArray) jsonObject.get("parking");

        for (int i=0; i<jsonArray.size(); i++) {
            Object ob = jsonArray.get(i);
            JSONObject object = (JSONObject) jsonParser.parse(ob.toString());

            String name = object.get("name").toString();
            JSONArray placesArray = (JSONArray) object.get("parking_places");

            ParkingPlaceEntity[] parkingPlaces = new ParkingPlaceEntity[placesArray.size()];

            for (int j=0; j<placesArray.size(); j++) {
                Object obj = placesArray.get(j);
                JSONObject place = (JSONObject) jsonParser.parse(obj.toString());
                String parking_name = place.get("parking_name").toString();
                String parking_number = place.get("place_number").toString();
                String place_id = place.get("place_id").toString();

                JSONArray settedCar = (JSONArray) place.get("setted_car");
                Object car_ob = settedCar.get(0);
                JSONObject car = (JSONObject) jsonParser.parse(car_ob.toString());
                String carNumber = car.get("carNumber").toString();
                String owner = car.get("owner").toString();

                CarEntity newCar = new CarEntity(carNumber, owner);
                ParkingPlaceEntity newPlace = new ParkingPlaceEntity(parking_name, parking_number, place_id, newCar);
                parkingPlaces[j] = newPlace;

            }



            parkingTable.put(name, new ParkingEntity(name, parkingPlaces));
        }
    }

    @Override
    public Optional<ParkingEntity> get(String name) {
        return Optional.of(parkingTable.get(name));
    }

    @Override
    public Hashtable<String, ParkingEntity> getAll() {
        return parkingTable;
    }

    @Override
    public void save() throws IOException {
        File file = Paths.get(System.getProperty("user.dir"), "resources/Parking.json").toFile();
        String str = this.toString();
        FileOutputStream outputStream = new FileOutputStream(file.toString());
        byte[] strToBytes = str.getBytes();
        outputStream.write(strToBytes);
        outputStream.close();
    }

    @Override
    public void delete(String name) throws IOException {
        parkingTable.remove(name);
        this.save();
    }

    @Override
    public void update(ParkingEntity parkingEntity, String name) throws IOException {
        parkingTable.remove(name);
        parkingTable.put(name, parkingEntity);
        this.save();
    }

    @Override
    public void add(ParkingEntity parkingEntity) throws IOException {
        parkingTable.put(parkingEntity.getName(), parkingEntity);
        this.save();
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{\n");
        sb.append("\"parking\" : [\n");
        for (String key: parkingTable.keySet()){
            sb.append("{\n");
            sb.append(parkingTable.get(key).toString());
            sb.append("},\n");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]\n}");
        return sb.toString();
    }
}

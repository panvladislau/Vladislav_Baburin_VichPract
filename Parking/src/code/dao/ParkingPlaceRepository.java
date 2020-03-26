package code.dao;

import code.dao.GenericDAO;
import code.dao.ParkingPlaceEntity;
import code.dao.UserEntity;
import code.domain.ParkingPlace;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Hashtable;
import java.util.Optional;

public class ParkingPlaceRepository implements GenericDAO<String, ParkingPlaceEntity> {
    Hashtable<String, ParkingPlaceEntity> parkingTable = new Hashtable<String, ParkingPlaceEntity>();

    public ParkingPlaceRepository() throws IOException, ParseException {
        String path = "D:\\reposirory\\lab1\\Vladislav_Baburin_VichPract\\Parking\\resources\\Parking.json";
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(path));
        JSONArray jsonArray = (JSONArray) jsonObject.get("parking");

        for (int i=0; i<jsonArray.size(); i++) {
            Object ob = jsonArray.get(i);
            JSONObject object = (JSONObject) jsonParser.parse(ob.toString());

            String parkingName = object.get("Parking name").toString();
            String placeNumber = object.get("Place number").toString();
            String locatedCarNumber = object.get("Located car number").toString();
            String id = object.get("Place id").toString();
            String currentUser = object.get("Current user").toString();

            parkingTable.put(id, new ParkingPlaceEntity(parkingName, placeNumber, locatedCarNumber, id, currentUser));
        }
    }

    @Override
    public Optional< ParkingPlaceEntity> get(String id) {
        return Optional.of(parkingTable.get(id));
    }

    @Override
    public Hashtable<String,  ParkingPlaceEntity> getAll() {
        return parkingTable;
    }

    @Override
    public void save() throws IOException {
        String fileName = "D:\\reposirory\\lab1\\Vladislav_Baburin_VichPract\\Parking\\resources\\Parking.json";
        String str = this.toString();
        FileOutputStream outputStream = new FileOutputStream(fileName);
        byte[] strToBytes = str.getBytes();
        outputStream.write(strToBytes);
        outputStream.close();
    }

    @Override
    public void delete(String id) throws IOException {
        parkingTable.remove(id);
        this.save();
    }

    @Override
    public void update(ParkingPlaceEntity parkingPlaceEntity, String id) throws IOException {
        parkingTable.remove(id);
        parkingTable.put(id, parkingPlaceEntity);
        this.save();
    }

    @Override
    public void add(ParkingPlaceEntity parkingPlaceEntity) throws IOException {
        parkingTable.put(parkingPlaceEntity.getId(), parkingPlaceEntity);
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
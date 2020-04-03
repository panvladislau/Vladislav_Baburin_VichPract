package code.dao;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.*;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Optional;

public class UserRepository implements GenericDAO<String, UserEntity> {
    Hashtable<String, UserEntity> usersTable = new Hashtable<String, UserEntity>();

    public UserRepository() throws IOException, ParseException, CloneNotSupportedException {
        File file = Paths.get(System.getProperty("user.dir"), "resources/Users.json").toFile();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(file.toString()));
        JSONArray jsonArray = (JSONArray) jsonObject.get("users");

        for (int i=0; i<jsonArray.size(); i++) {
            Object ob = jsonArray.get(i);
            JSONObject object = (JSONObject) jsonParser.parse(ob.toString());

            String username = object.get("username").toString();
            String password = object.get("password").toString();
            boolean isAdmin = Boolean.valueOf(object.get("isAdmin").toString());
            JSONArray carsArray = (JSONArray) object.get("cars");
            if (carsArray == null) System.out.print("OBOSRALSA");
            CarEntity[]cars = new CarEntity[carsArray.size()];

            for (int j=0; j<carsArray.size(); j++) {
                Object obj = carsArray.get(j);
                JSONObject car = (JSONObject) jsonParser.parse(obj.toString());
                String carNumber = car.get("carNumber").toString();
                String owner = car.get("owner").toString();
                CarEntity newCar = new CarEntity(carNumber, owner);
                cars[j] = newCar;

            }



            usersTable.put(username, new UserEntity(username, password, isAdmin, cars));
        }
    }

    @Override
    public Optional<UserEntity> get(String username) {
        return Optional.of(usersTable.get(username));
    }

    @Override
    public Hashtable<String, UserEntity> getAll() {
        return usersTable;
    }

    @Override
    public void save() throws IOException {
        File file = Paths.get(System.getProperty("user.dir"), "resources/Users.json").toFile();
        String str = this.toString();
        FileOutputStream outputStream = new FileOutputStream(file.toString());
        byte[] strToBytes = str.getBytes();
        outputStream.write(strToBytes);
        outputStream.close();
    }

    @Override
    public void delete(String username) throws IOException {
        usersTable.remove(username);
        this.save();
    }

    @Override
    public void update(UserEntity userEntity, String username) throws IOException {
        usersTable.remove(username);
        usersTable.put(username, userEntity);
        this.save();
    }

    @Override
    public void add(UserEntity userEntity) throws IOException {
        usersTable.put(userEntity.getUsername(), userEntity);
        this.save();
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{\n");
        sb.append("\"users\" : [\n");
        for (String key: usersTable.keySet()){
            sb.append("{\n");
            sb.append(usersTable.get(key).toString());
            sb.append("},\n");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]\n}");
        return sb.toString();
    }
}

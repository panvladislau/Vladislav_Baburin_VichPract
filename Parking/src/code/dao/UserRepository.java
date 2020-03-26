package code.dao;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Hashtable;
import java.util.Optional;

public class UserRepository implements GenericDAO<String, UserEntity> {
    Hashtable<String, UserEntity> usersTable = new Hashtable<String, UserEntity>();

    public UserRepository() throws IOException, ParseException {
        ClassLoader classLoader = UserRepository.class.getClassLoader();
        File file = new File(classLoader.getResource("Users.json").getFile());
        String path = file.getAbsolutePath();
        System.out.print(path);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(path));
        JSONArray jsonArray = (JSONArray) jsonObject.get("users");


        for (Object o : jsonArray) {
            JSONObject object = (JSONObject) jsonParser.parse(o.toString());

            String username = object.get("username").toString();
            String password = object.get("password").toString();
            boolean isAdmin = Boolean.valueOf(object.get("isAdmin").toString());

            usersTable.put(username, new UserEntity(username, password, isAdmin));
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
        ClassLoader classLoader = UserRepository.class.getClassLoader();
        File file = new File(classLoader.getResource("Users").getFile());
        String fileName = file.getAbsolutePath();
        String str = this.toString();
        FileOutputStream outputStream = new FileOutputStream(fileName);
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
        sb.append("\"clients\" : [\n");
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

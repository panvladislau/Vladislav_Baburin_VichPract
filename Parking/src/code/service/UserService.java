package code.service;

import code.dao.UserEntity;
import code.dao.UserRepository;
import org.json.simple.parser.ParseException;

import code.domain.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class UserService {
    UserRepository userRepository = new UserRepository();
    UserMapper mapper = new UserMapper();

    public UserService() throws ParseException, java.text.ParseException, IOException {
    }

    public ArrayList<String> getUserStrings() throws Exception {
        ArrayList<User> users = getUser();
        ArrayList<String> userStrings = new ArrayList<>();

        for (User user: users) {
            userStrings.add(user.toString());
        }

        return userStrings;
    }

    public ArrayList<User> getUser() throws Exception{
        Hashtable<String, UserEntity> usersInFile = userRepository.getAll();
        ArrayList<User> users = new ArrayList<>();

        for (String key : usersInFile.keySet()) {
            UserEntity userEntity = usersInFile.get(key);
            User user = mapper.mapToUser(userEntity);
            users.add(user);
        }

        return users;
    }

    public void addUser(User user) throws IOException {
        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setAdmin(user.getIsAdmin());
        userRepository.add(entity);
    }

    public void deleteUser(User user) throws IOException {
        if (user.getIsAdmin()) {
            String username = user.getUsername();
            userRepository.delete(username);
        }
    }
    //will be used by admin
}

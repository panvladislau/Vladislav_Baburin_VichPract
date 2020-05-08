package code.service;

import code.domain.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    boolean create(User user);
    User read(int id);
    boolean update(User user);
    boolean delete(int id);
    ArrayList<User> readAll();

    void listAllUsers();
    void showUser(int id);
}

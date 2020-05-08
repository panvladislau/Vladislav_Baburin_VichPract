package code.service.impl;

import code.dao.DAOFactory;
import code.dao.UserDAO;
import code.domain.User;
import code.exception.ValidationException;
import code.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static final UserDAO dao = DAOFactory.getUserDAO();

    @Override
    public boolean create(User user) {
        checkUser(user);
        return dao.create(user);
    }

    @Override
    public User read(int id) {
        return dao.read(id).orElse(null);
    }

    @Override
    public boolean update(User user) {
        checkUser(user);
        return dao.update(user);
    }

    @Override
    public boolean delete(int id) {
        return dao.delete(id);
    }

    @Override
    public void listAllUsers() {
        List<User> users = dao.readAll();
        for (User user: users) {
            System.out.println(user + "\n");
        }
    }

    @Override
    public void showUser(int id) {
        if (dao.read(id).isEmpty()) {
            System.out.println("No such elements");
        } else {
            System.out.println(dao.read(id).get());
        }
    }

    @Override
    public ArrayList<User> readAll(){
        return dao.readAll();
    }

    private void checkUser(User user) {
        if (user == null) {
            throw new ValidationException("Invalid user.");
        }

        String username = user.getUsername();
        if (username == null || username.isEmpty()) {
            throw new ValidationException("Username is required.");
        }

        String password = user.getPassword();
        if (password == null || password.isEmpty()) {
            throw new ValidationException("Password is required.");
        }
    }
}

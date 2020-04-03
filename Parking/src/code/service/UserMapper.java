package code.service;

import code.dao.CarEntity;
import code.dao.UserEntity;
import code.domain.User;

public class UserMapper {

    public User mapToUser(UserEntity userEntity) throws Exception {
        String username = userEntity.getUsername();
        String password = userEntity.getPassword();
        boolean isAdmin = userEntity.isAdmin();
        CarEntity[] cars = userEntity.getCars();
        return new User(username, password, isAdmin, cars);
    }
}

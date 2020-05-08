package code.dao.impl;

import code.dao.DB;
import code.dao.UserDAO;
import code.domain.CarEntity;
import code.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    private Connection connection;
    private String database;
    private static final Logger logger = LogManager.getLogger(CarDAOImpl.class.getName());

    public UserDAOImpl(DB databaseUtil) {
        try {
            this.connection = databaseUtil.getConnection();
            this.database = databaseUtil.getDatabase();
        }
        catch (SQLException | ClassNotFoundException e){
            logger.error(e.toString());
            DB.connectionFailed();
        }
    }

    @Override
    public boolean create(User entity) {
        try {
            String query = String.format(
                    "INSERT INTO dbo.Users (username, password, isAdmin)" +
                            " VALUES ('%s', '%s', '%s')",
                    entity.getUsername(), entity.getPassword(), entity.getIsAdmin()
            );
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if (preparedStatement.executeUpdate() == 1) {
                logger.debug("Object has been created");
                return true;
            }
        }
        catch (SQLException e){
            System.out.println(e.toString());
            logger.error(e.toString());
        }
        logger.debug("Object hasn't been created");
        return false;
    }

    public Optional<User> read(int id) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("SELECT * FROM dbo.Users WHERE id=%d", id);
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                logger.debug("Object has been read successful");
                return Optional.of(userFromResultSet(resultSet));
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            logger.error(e.toString());
        }
        logger.debug("Object has been read as empty one");
        return Optional.empty();
    }

    @Override
    public ArrayList<User> readAll() {
        try {
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM dbo.Users";
            ResultSet resultSet =  statement.executeQuery(query);

            ArrayList<User> users = new ArrayList<>();

            while(resultSet.next()) {
                users.add(userFromResultSet(resultSet));
            }
            statement.close();
            logger.debug("Objects have been read successful");
            return users;
        } catch (SQLException e) {
            System.out.println(e.toString());
            logger.error(e.toString());
        }
        logger.debug("Objects have been read as empty one");
        return null;
    }

    @Override
    public boolean update(User entity) {
        try {
            String query = String.format(
                    "UPDATE dbo.Users SET username='%s', password='%s', isAdmin='%s' WHERE id=%d",
                    entity.getUsername(), entity.getPassword(), entity.getIsAdmin(), entity.getId()
            );
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if (preparedStatement.executeUpdate() == 1) {
                logger.debug("Objects has been updated successful");
                return true;
            }
        }
        catch (SQLException e){
            System.out.println(e.toString());
            logger.error(e.toString());
        }
        logger.debug("Objects hasn't been updated");
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("DELETE FROM dbo.Users WHERE id=%d", id);
            if (statement.executeUpdate(query) == 1) {
                statement.close();
                logger.debug("Objects has been deleted successful");
                return true;
            }
        }
        catch (SQLException e){
            System.out.println(e.toString());
            logger.error(e.toString());
        }
        logger.debug("Objects hasn't been deleted");
        return false;
    }

    private User userFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getInt("id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setAdmin(resultSet.getBoolean("isAdmin"));

        DB db = new DB(database);
        CarDAOImpl cars = new CarDAOImpl(db);
        List<CarEntity> carList = cars.findByOwner(user.getUsername());
        CarEntity[] carsToAdd = new CarEntity[carList.size()];
        for (int i=0; i<carList.size(); i++){
            carsToAdd[i] = carList.get(i);
        }

        user.setCars(carsToAdd);

        return user;
    }

}

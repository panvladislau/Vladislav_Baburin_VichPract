package code.dao.impl;


import code.dao.CarDAO;
import code.dao.DB;
import code.domain.CarEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDAOImpl implements CarDAO {
    private Connection connection;
    private static final Logger logger = LogManager.getLogger(CarDAOImpl.class.getName());

    public CarDAOImpl(DB databaseUtil) {
        try {
            this.connection = databaseUtil.getConnection();
        }
        catch (SQLException | ClassNotFoundException e){
            logger.error(e.toString());
            DB.connectionFailed();
        }
    }


    @Override
    public ArrayList<CarEntity> findByOwner(String owner) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("SELECT * FROM dbo.Cars WHERE owner='%s'", owner);
            ResultSet resultSet = statement.executeQuery(query);

            ArrayList<CarEntity> searchResult = new ArrayList<>();
            while(resultSet.next()) {
                searchResult.add(carFromResultSet(resultSet));
            }
            statement.close();
            logger.debug("Objects have been found");
            return searchResult;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        logger.debug("Objects haven't been found");
        return null;
    }

    @Override
    public boolean create(CarEntity entity) {
        try {
            String query = String.format(
                    "INSERT INTO dbo.Cars (car_number, owner)" +
                            " VALUES ('%s', '%s')",
                     entity.getCarNumber(), entity.getOwner()
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

    public Optional<CarEntity> read(int id) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("SELECT * FROM dbo.Cars WHERE id=%d", id);
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                logger.debug("Object has been read successful");
                return Optional.of(carFromResultSet(resultSet));
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
    public ArrayList<CarEntity> readAll() {
        try {
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM dbo.Cars";
            ResultSet resultSet =  statement.executeQuery(query);

            ArrayList<CarEntity> cars = new ArrayList<>();

            while(resultSet.next()) {
                cars.add(carFromResultSet(resultSet));
            }
            statement.close();
            logger.debug("Objects have been read successful");
            return cars;
        } catch (SQLException e) {
            System.out.println(e.toString());
            logger.error(e.toString());
        }
        logger.debug("Objects have been read as empty one");
        return null;
    }

    @Override
    public boolean update(CarEntity entity) {
        try {
            String query = String.format(
                    "UPDATE dbo.Cars SET car_number='%s', owner='%s' WHERE id=%d",
                     entity.getCarNumber(), entity.getOwner(), entity.getId()
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
            String query = String.format("DELETE FROM dbo.Cars WHERE id=%d", id);
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

    private CarEntity carFromResultSet(ResultSet resultSet) throws SQLException {
        CarEntity car = new CarEntity();

        car.setId(resultSet.getInt("id"));
        car.setCarNumber(resultSet.getString("car_number"));
        car.setOwner(resultSet.getString("owner"));

        return car;
    }

}

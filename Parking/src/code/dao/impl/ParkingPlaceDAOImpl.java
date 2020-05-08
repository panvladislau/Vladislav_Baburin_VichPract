package code.dao.impl;

import code.dao.DB;
import code.dao.ParkingPlaceDAO;
import code.domain.CarEntity;
import code.domain.ParkingPlaceEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingPlaceDAOImpl implements ParkingPlaceDAO {
    private Connection connection;
    private String database;
    private static final Logger logger = LogManager.getLogger(ParkingPlaceDAOImpl.class.getName());

    public ParkingPlaceDAOImpl(DB databaseUtil) {
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
    public ArrayList<ParkingPlaceEntity> findByParkingName(String parkingName) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("SELECT * FROM dbo.Parking_places WHERE parking_name='%s'", parkingName);
            ResultSet resultSet = statement.executeQuery(query);

            ArrayList<ParkingPlaceEntity> searchResult = new ArrayList<>();
            while(resultSet.next()) {
                searchResult.add(parkingPlaceFromResultSet(resultSet));
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
    public boolean create(ParkingPlaceEntity entity) {
        try {
            String query = String.format(
                    "INSERT INTO dbo.Parking_places (parking_name, place_number, car_number)" +
                            " VALUES ('%s', '%s', '%s')",
                     entity.getParkingName(), entity.getPlaceNumber(), entity.getSettedCar().getCarNumber()
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

    public Optional<ParkingPlaceEntity> read(int id) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("SELECT * FROM dbo.Parking_places WHERE id=%d", id);
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                logger.debug("Object has been read successful");
                return Optional.of(parkingPlaceFromResultSet(resultSet));
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
    public ArrayList<ParkingPlaceEntity> readAll() {
        try {
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM dbo.Parking_places";
            ResultSet resultSet =  statement.executeQuery(query);

            ArrayList<ParkingPlaceEntity> parkingPlace = new ArrayList<>();

            while(resultSet.next()) {
                parkingPlace.add(parkingPlaceFromResultSet(resultSet));
            }
            statement.close();
            logger.debug("Objects have been read successful");
            return parkingPlace;
        } catch (SQLException e) {
            System.out.println(e.toString());
            logger.error(e.toString());
        }
        logger.debug("Objects have been read as empty one");
        return null;
    }

    @Override
    public boolean update(ParkingPlaceEntity entity) {
        try {
            String query = String.format(
                    "UPDATE dbo.Parking_places SET parking_name='%s', place_number='%s', car_number='%s' WHERE id=%d",
                    entity.getParkingName(), entity.getPlaceNumber(), entity.getSettedCar().getCarNumber(), entity.getId()
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
            String query = String.format("DELETE FROM dbo.Parking_places WHERE id=%d", id);
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

    private ParkingPlaceEntity parkingPlaceFromResultSet(ResultSet resultSet) throws SQLException {
        ParkingPlaceEntity parkingPlace = new ParkingPlaceEntity();

        parkingPlace.setId(resultSet.getInt("id"));
        parkingPlace.setParkingName(resultSet.getString("parking_name"));
        parkingPlace.setPlaceNumber(resultSet.getString("place_number"));

        DB db = new DB(database);
        CarDAOImpl cars = new CarDAOImpl(db);
        List<CarEntity> carList = cars.readAll();
        if (resultSet.getString("car_number").equals("")){
            CarEntity settedCar = new CarEntity();
            settedCar.setOwner("");
            settedCar.setCarNumber("");
            parkingPlace.setSettedCar(settedCar);
            return parkingPlace;
        }
        for (int i=0; i<carList.size(); i++){
            if (carList.get(i).getCarNumber().equals(resultSet.getString("car_number")))
                parkingPlace.setSettedCar(carList.get(i));
        }



        return parkingPlace;
    }

}

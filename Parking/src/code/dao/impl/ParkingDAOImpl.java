package code.dao.impl;

import code.dao.DB;
import code.dao.ParkingDAO;
import code.domain.Parking;
import code.domain.ParkingPlaceEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingDAOImpl implements ParkingDAO {
    private Connection connection;
    private String database;
    private static final Logger logger = LogManager.getLogger(ParkingDAOImpl.class.getName());

    public ParkingDAOImpl(DB databaseUtil) {
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
    public boolean create(Parking entity) {
        try {
            String query = String.format(
                    "INSERT INTO dbo.Parking (parking_name)" +
                            " VALUES ('%s')",
                    entity.getName()
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

    public Optional<Parking> read(int id) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("SELECT * FROM dbo.Parking WHERE id=%d", id);
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                logger.debug("Object has been read successful");
                return Optional.of(parkingFromResultSet(resultSet));
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
    public ArrayList<Parking> readAll() {
        try {
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM dbo.Parking";
            ResultSet resultSet =  statement.executeQuery(query);

            ArrayList<Parking> parking = new ArrayList<>();

            while(resultSet.next()) {
                parking.add(parkingFromResultSet(resultSet));
            }
            statement.close();
            logger.debug("Objects have been read successful");
            return parking;
        } catch (SQLException e) {
            System.out.println(e.toString());
            logger.error(e.toString());
        }
        logger.debug("Objects have been read as empty one");
        return null;
    }

    @Override
    public boolean update(Parking entity) {
        try {
            String query = String.format(
                    "UPDATE dbo.Parking SET parking_name='%s' WHERE id=%d",
                     entity.getName(), entity.getId()
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
            String query = String.format("DELETE FROM dbo.Parking WHERE id=%d", id);
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

    private Parking parkingFromResultSet(ResultSet resultSet) throws SQLException {
        Parking parking = new Parking();

        parking.setId(resultSet.getInt("id"));
        parking.setName(resultSet.getString("parking_name"));

        DB db = new DB(database);
        ParkingPlaceDAOImpl parkingPlaces = new ParkingPlaceDAOImpl(db);
        List<ParkingPlaceEntity> parkingPlaceList = parkingPlaces.findByParkingName(parking.getName());
        ParkingPlaceEntity[] parkingPlacesToAdd = new ParkingPlaceEntity[parkingPlaceList.size()];
        for (int i=0; i<parkingPlaceList.size(); i++){
            parkingPlacesToAdd[i] = parkingPlaceList.get(i);
        }

        parking.setParkingPlaces(parkingPlacesToAdd);

        return parking;
    }

}
package code.dao;

import code.dao.impl.CarDAOImpl;
import code.dao.impl.ParkingDAOImpl;
import code.dao.impl.ParkingPlaceDAOImpl;
import code.dao.impl.UserDAOImpl;

public final class DAOFactory {
    private static final DB databaseConnection = new DB("Parking");

    private static final UserDAO userDAO = new UserDAOImpl(databaseConnection);
    private static final CarDAO carDAO = new CarDAOImpl(databaseConnection);
    private static final ParkingDAO parkingDAO = new ParkingDAOImpl(databaseConnection);
    private static final ParkingPlaceDAO parkingPlaceDAO = new ParkingPlaceDAOImpl(databaseConnection);

    public static UserDAO getUserDAO() {
        return userDAO;
    }
    public static CarDAO getCarDAO() {
        return carDAO;
    }
    public static ParkingDAO getParkingDAO() {
        return parkingDAO;
    }
    public static ParkingPlaceDAO getParkingPlaceDAO() {
        return parkingPlaceDAO;
    }
}
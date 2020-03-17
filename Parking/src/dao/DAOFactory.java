package dao;

import dao.impl.CarDAOImplJSON;
import dao.impl.CarOwnerDAOImplJSON;
import dao.impl.ParkingDAOImplJSON;

public final class DAOFactory {

    private static final CarDAO carDAO = new CarDAOImplJSON();
    private static final CarOwnerDAO carOwnerDAO = new CarOwnerDAOImplJSON();
    private static final ParkingDAO parkingDAO = new ParkingDAOImplJSON();

    public static CarDAO getCarDAO() {
        return carDAO;
    }
    public static CarOwnerDAO getCarOwnerDAO() {
        return carOwnerDAO;
    }
    public static ParkingDAO getParkingDAO() { return parkingDAO;}
}

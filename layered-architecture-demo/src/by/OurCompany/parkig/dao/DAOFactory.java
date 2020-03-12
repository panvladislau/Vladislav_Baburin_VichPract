package dao;

import dao.impl.CarDAOImplJSON;
import dao.impl.CarOwnerDAOImplJSON;

public final class DAOFactory {

  private static final CarDAO carDAO = new CarDAOImplJSON();
  private static final CarOwnerDAO carOwnerDAO = new CarOwnerDAOImplJSON();

  public static CarDAO getCarDAO() {
    return carDAO;
  }
  public static CarOwnerDAO getCarOwnerDAO() {
    return carOwnerDAO;
  }
}

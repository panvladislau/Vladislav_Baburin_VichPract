package domain;
import java.util.Objects;
// Договор
public class Order extends AbstractEntity<Long> {
  public void Ordering(Car car, ParkingPlace parkingPlace){
    parkingPlace.setPlaceCar(car);
    parkingPlace.setPlaceOccupation(true);
  }
}

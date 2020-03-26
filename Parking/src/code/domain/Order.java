package code.domain;

// Договор
public class Order extends AbstractEntity<Long> {
    public void Ordering(Car car, ParkingPlace parkingPlace){
        if (!parkingPlace.getPlaceOccupation()) {
            parkingPlace.setPlacedCar(car);
            parkingPlace.setPlaceOccupation(true);
        }
        else {
            System.out.println("Place already occupied");
        }
    }
}

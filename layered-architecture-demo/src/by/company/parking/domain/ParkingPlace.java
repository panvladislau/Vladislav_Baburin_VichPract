package domain;

public class ParkingPlace extends AbstractEntity<Long> {
  private string placeNumber;
  private bool placeOccupation;
  private Car placedCar;

    public ParkingPlace(string placeNumber, bool placeOccupation, Car placedCar) {
        this.placeNumber = placeNumber;
        this.placeOccupation = placeOccupation;
        this.placedCar = placedCar;
    }

    public void setPlaceNumber(string placeNumber) {
        this.placeNumber = placeNumber;
    }

    public void setPlaceOccupation(bool placeOccupation) {
        this.placeOccupation = placeOccupation;
    }

    public void setPlacedCar(Car placedCar) {
        this.placedCar = placedCar;
    }

    public string getPlaceNumber() {
        return placeNumber;
    }

    public bool getPlaceOccupation() {
        return placeOccupation;
    }

    public Car getPlacedCar() {
        return placedCar;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        ParkingPlace that = (ParkingPlace) object;
        return java.util.Objects.equals(placeNumber, that.placeNumber) &&
                java.util.Objects.equals(placeOccupation, that.placeOccupation) &&
                java.util.Objects.equals(placedCar, that.placedCar);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), placeNumber, placeOccupation, placedCar);
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "ParkingPlace{" +
                "placeNumber=" + placeNumber +
                ", placeOccupation=" + placeOccupation +
                ", placedCar=" + placedCar +
                '}';
    }
}

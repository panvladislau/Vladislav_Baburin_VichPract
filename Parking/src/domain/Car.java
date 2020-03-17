package domain;

import java.util.Objects;
import domain.CarOwner;

public class Car extends AbstractEntity<Long> {

    private String plate = "nope";
    private CarOwner owner = new CarOwner();
    private String color = "nope";

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public CarOwner getOwner() {
        return owner;
    }

    public void setOwner(CarOwner owner) {
        this.owner = owner;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(plate, car.plate) && Objects.equals(owner, car.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), plate, owner);
    }

    @Override
    public String toString() {
        return "Car{"
                + "id='"
                + getId()
                + '\''
                + ", plate='"
                + plate
                + '\''
                + ", owner="
                + owner
                + ", color='"
                + color
                + '\''
                + '}';
    }
}

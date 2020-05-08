package code.domain;

import java.util.Objects;

public class ParkingPlaceEntity extends AbstractEntity<Integer> {
    private String parkingName;
    private String placeNumber;
    private CarEntity settedCar;

    public ParkingPlaceEntity(){
        this.parkingName = null;
        this.placeNumber = null;
    }

    public ParkingPlaceEntity(String parkingName, String placeNumber,  CarEntity settedCar) throws NullPointerException{
        this.placeNumber = placeNumber;
        this.parkingName = parkingName;
        this.settedCar = settedCar;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public String getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(String placeNumber) {
        this.placeNumber = placeNumber;
    }

    public CarEntity getSettedCar() {
        return settedCar;
    }

    public void setSettedCar(CarEntity settedCar) {
        this.settedCar = settedCar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ParkingPlaceEntity that = (ParkingPlaceEntity) o;
        return Objects.equals(parkingName, that.parkingName) &&
                Objects.equals(placeNumber, that.placeNumber) &&
                Objects.equals(settedCar, that.settedCar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkingName, placeNumber, settedCar);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("\"id\" : \"").append(getId()).append("\",\n");
        sb.append("\"parking_name\" : \"").append(parkingName).append("\",\n");
        sb.append("\"place_number\" : \"").append(placeNumber).append("\",\n");
        sb.append("\"setted_car\" : [\n").append("{\n").append(settedCar.toString()).append("}\n").append("]");
        sb.append('\n');
        return sb.toString();
    }
}
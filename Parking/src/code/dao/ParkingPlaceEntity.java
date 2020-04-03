package code.dao;

import java.util.Objects;

public class ParkingPlaceEntity {
    private String parkingName;
    private String placeNumber;
    private String id;
    private CarEntity settedCar;

    public ParkingPlaceEntity(){
        this.parkingName = null;
        this.placeNumber = null;
        this.id = null;
    }

    public ParkingPlaceEntity(String parkingName, String placeNumber, String id, CarEntity settedCar) throws NullPointerException{
        this.placeNumber = placeNumber;
        this.parkingName = parkingName;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        ParkingPlaceEntity that = (ParkingPlaceEntity) o;
        return Objects.equals(parkingName, that.parkingName) &&
                Objects.equals(placeNumber, that.placeNumber) &&
                Objects.equals(id, that.id) &&
                Objects.equals(settedCar, that.settedCar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkingName, placeNumber, id, settedCar);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("\"parking_name\" : \"").append(parkingName).append("\",\n");
        sb.append("\"place_number\" : \"").append(placeNumber).append("\",\n");
        sb.append("\"place_id\" : \"").append(id).append("\",\n");
        sb.append("\"setted_car\" : [\n").append("{\n").append(settedCar.toString()).append("}\n").append("]");
        sb.append('\n');
        return sb.toString();
    }
}
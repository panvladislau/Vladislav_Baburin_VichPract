package code.dao;

import java.util.Arrays;
import java.util.Objects;

public class ParkingEntity {
    private String name;
    private ParkingPlaceEntity[] parkingPlaces;

    public ParkingEntity(){
        this.name = "";
    }

    public ParkingEntity(String name, ParkingPlaceEntity[] parkingPlaceEntities) throws NullPointerException{
        this.name = name;
        this.parkingPlaces = parkingPlaceEntities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParkingPlaceEntity[] getParkingPlaces() {
        return parkingPlaces;
    }

    public void setParkingPlaces(ParkingPlaceEntity[] parkingPlaces) {
        this.parkingPlaces = parkingPlaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingEntity that = (ParkingEntity) o;
        return Objects.equals(name, that.name) &&
                Arrays.equals(parkingPlaces, that.parkingPlaces);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name);
        result = 31 * result + Arrays.hashCode(parkingPlaces);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("\"name\" : \"").append(name).append("\",\n");
        sb.append("\"parking_places\" : [\n");
        for (int i = 0; i< parkingPlaces.length; i++){
            sb.append("{\n");
            sb.append(parkingPlaces[i].toString());
            sb.append("},\n");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]\n");
        return sb.toString();
    }
}

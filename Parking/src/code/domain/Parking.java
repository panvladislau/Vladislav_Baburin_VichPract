package code.domain;

public class Parking extends AbstractEntity<Long> {
    private String parkingName = "nope";
    private ParkingPlace[] parkingPlaces;


    public Parking() {
        this.parkingName = parkingName;
        this.parkingPlaces = parkingPlaces;
    }

    public Parking(String parkingName, ParkingPlace[] parkingPlaces) {
        this.parkingName = parkingName;
        this.parkingPlaces = parkingPlaces;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public void setParkingPlaces(ParkingPlace[] parkingPlaces) {
        this.parkingPlaces = parkingPlaces;
    }

    public String getParkingName() {
        return parkingName;
    }

    public ParkingPlace[] getParkingPlaces() {
        return parkingPlaces;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Parking parking = (Parking) object;
        return java.util.Objects.equals(parkingName, parking.parkingName) &&
                java.util.Arrays.equals(parkingPlaces, parking.parkingPlaces);
    }

    public int hashCode() {
        int result = java.util.Objects.hash(super.hashCode(), parkingName);
        result = 31 * result + java.util.Arrays.hashCode(parkingPlaces);
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Parking{" +
                "parkingName=" + parkingName +
                ", parkingPlaces=" + java.util.Arrays.toString(parkingPlaces) +
                '}';
    }
}
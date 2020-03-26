package code.dao;

public class ParkingPlaceEntity {
    private String parkingName;
    private String placeNumber;
    private String locatedCarNumber;
    private String id;
    private String currentUser;

    public ParkingPlaceEntity(){
        this.parkingName = null;
        this.placeNumber = null;
        this.locatedCarNumber = null;
        this.id = null;
        this.currentUser = null;
    }

    public ParkingPlaceEntity(String parkingName, String placeNumber, String locatedCarNumber, String id, String currentUser){
        this.placeNumber = placeNumber;
        this.parkingName = parkingName;
        this.locatedCarNumber = locatedCarNumber;
        this.id = id;
        this.currentUser = currentUser;
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


    public String getLocatedCarNumber() {
        return locatedCarNumber;
    }

    public void setLocatedCarNumber(String locatedCarNumber) {
        this.locatedCarNumber = locatedCarNumber;
    }

    public String getCurrentUser() { return currentUser;}

    public void setCurrentUser(String currentUser) { this.currentUser = currentUser;}

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("\"Parking name\" : \"").append(parkingName).append("\",\n");
        sb.append("\"Place number\" : \"").append(placeNumber).append("\",\n");
        sb.append("\"Located car number\" : \"").append(locatedCarNumber).append("\",\n");
        sb.append("\"Place id\" : \"").append(id).append("\",\n");
        sb.append("\"Current user\" : \"").append(currentUser).append("\"\n");
        sb.append('\n');
        return sb.toString();
    }
}
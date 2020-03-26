package code.domain;

public class ParkingPlace {
    private String parkingName;
    private String placeNumber;
    private String locatedCarNumber;
    private String id;
    private String currentUser;

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

    public String getLocatedCarNumber() {
        return locatedCarNumber;
    }

    public void setLocatedCarNumber(String locatedCarNumber) {
        this.locatedCarNumber = locatedCarNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public ParkingPlace (){
        parkingName = "";
        placeNumber = "";
        locatedCarNumber = "";
        id = "";
        currentUser = "";
    }

    public ParkingPlace (String parkingName, String placeNumber, String locatedCarNumber, String id, String currentUser){
        this.parkingName =  parkingName;
        this.placeNumber = placeNumber;
        this.locatedCarNumber = locatedCarNumber;
        this.id = id;
        this.currentUser = currentUser;
    }

    @Override
    public String toString() {
        return "Parking name: ".concat(parkingName).concat("\n")
                .concat("Place number: ").concat(placeNumber).concat("\n")
                .concat("Located car number: ").concat(locatedCarNumber).concat("\n")
                .concat("Id: ").concat(id).concat("\n")
                .concat("Current user: ").concat(currentUser).concat("\n")
                .concat("\n");
    }
}
package code.domain;

public class CarOwner extends AbstractEntity<Long> {
    private String username = "nope";
    private String carOwnerName = "nope";
    private String carOwnerSecondName = "nope";

    public CarOwner() {
        this.username = username;
        this.carOwnerName = carOwnerName;
        this.carOwnerSecondName = carOwnerSecondName;
    }

    public CarOwner(String username, String carOwnerName, String carOwnerSecondname) {
        this.username = username;
        this.carOwnerName = carOwnerName;
        this.carOwnerSecondName = carOwnerSecondName;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setCarOwnerName(String carOwnerName) {
        this.carOwnerName = carOwnerName;
    }

    public void setCarOwnerSecondName(String carOwnerSecondName) {
        this.carOwnerSecondName = carOwnerSecondName;
    }

    public String getUsername() {
        return username;
    }


    public String getCarOwnerName() {
        return carOwnerName;
    }

    public String getCarOwnerSecondName() {
        return carOwnerSecondName;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        CarOwner carOwner = (CarOwner) object;
        return java.util.Objects.equals(username, carOwner.username) &&
                java.util.Objects.equals(carOwnerName, carOwner.carOwnerName) &&
                java.util.Objects.equals(carOwnerSecondName, carOwner.carOwnerSecondName);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), username, carOwnerName, carOwnerSecondName);
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "CarOwner{" +
                "username=" + username +
                ", carOwnerName=" + carOwnerName +
                ", carOwnerSecondname=" + carOwnerSecondName +
                '}';
    }
}
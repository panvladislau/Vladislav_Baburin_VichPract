package domain;

import java.util.Objects;

public class CarOwner extends AbstractEntity<Long> {
    private string username;
    private string password;
    private string carOwnerName;
    private string carOwnerSecondname;

    public CarOwner(string username, string password, string carOwnerName, string carOwnerSecondname) {
        this.username = username;
        this.password = password;
        this.carOwnerName = carOwnerName;
        this.carOwnerSecondname = carOwnerSecondname;
    }

    public void setUsername(string username) {
        this.username = username;
    }

    public void setPassword(string password) {
        this.password = password;
    }

    public void setCarOwnerName(string carOwnerName) {
        this.carOwnerName = carOwnerName;
    }

    public void setCarOwnerSecondname(string carOwnerSecondname) {
        this.carOwnerSecondname = carOwnerSecondname;
    }

    public void setCar(string car) {
        this.car = car;
    }

    public string getUsername() {
        return username;
    }

    public string getPassword() {
        return password;
    }

    public string getCarOwnerName() {
        return carOwnerName;
    }

    public string getCarOwnerSecondname() {
        return carOwnerSecondname;
    }

    public string getCar() {
        return car;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        CarOwner carOwner = (CarOwner) object;
        return java.util.Objects.equals(username, carOwner.username) &&
                java.util.Objects.equals(password, carOwner.password) &&
                java.util.Objects.equals(carOwnerName, carOwner.carOwnerName) &&
                java.util.Objects.equals(carOwnerSecondname, carOwner.carOwnerSecondname);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), username, password, carOwnerName, carOwnerSecondname);
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "CarOwner{" +
                "username=" + username +
                ", password=" + password +
                ", carOwnerName=" + carOwnerName +
                ", carOwnerSecondname=" + carOwnerSecondname +
                '}';
    }
}

package code.domain;

import code.dao.CarEntity;

import java.util.Arrays;
import java.util.Objects;

public class User {
    private String username;
    private String password;
    private boolean isAdmin;
    private CarEntity[] cars;

    public String getUsername() {
        return  username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getIsAdmin() { return isAdmin;}

    public CarEntity[] getCars() { return cars;}

    public void setUsername(String name) {
        this. username = name;
    }

    public void setPassword(String password) {
        this. password = password;
    }

    public void setAdmin(boolean isAdmin) { this.isAdmin = isAdmin;}

    public void setCars(CarEntity[] cars)  {
        this.cars = cars;
    }

    public User(){
        username = "";
        password = "";
    }

    public User(String name, String password, boolean isAdmin, CarEntity[] cars) {
        this.username =  name;
        this.password = password;
        this.isAdmin = isAdmin;
        this.cars = cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isAdmin == user.isAdmin &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Arrays.equals(cars, user.cars);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(username, password, isAdmin);
        result = 31 * result + Arrays.hashCode(cars);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("\"username\" : \"").append(username).append("\",\n");
        sb.append("\"password\" : \"").append(password).append("\",\n");
        sb.append("\"cars\" : [\n");
        for (int i=0; i<cars.length; i++){
            sb.append("{\n");
            sb.append(cars[i].toString());
            sb.append("},\n");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]\n");
        return sb.toString();
    }

    public boolean equals(User user) {
        return this.getUsername().equals(user.getUsername()) && this.getPassword().equals(user.getPassword());
    }
}
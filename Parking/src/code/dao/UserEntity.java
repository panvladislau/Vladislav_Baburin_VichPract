package code.dao;


import java.util.Arrays;
import java.util.Objects;

public class UserEntity {
    private String username;
    private String password;
    private boolean isAdmin;
    private CarEntity[] cars;

    public UserEntity(){
        this.username = "";
        this.password = "";
        this.isAdmin = false;
    }

    public UserEntity(String username, String password, boolean isAdmin, CarEntity[] cars) throws NullPointerException {
        this.username = username;
        this.isAdmin = isAdmin;
        this.password = password;
        this.cars = cars;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() { return password; }

    public boolean isAdmin() {
        return isAdmin;
    }

    public CarEntity[] getCars() { return cars;}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) { this.password = password;}

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setCars(CarEntity[] cars)  {
        this.cars = cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return isAdmin == that.isAdmin &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Arrays.equals(cars, that.cars);
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
        sb.append("\"isAdmin\" : ").append(isAdmin).append(",\n");
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
}
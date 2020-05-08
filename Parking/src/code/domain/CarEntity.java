package code.domain;

import java.util.Objects;

public class CarEntity extends AbstractEntity<Integer>{
    private String carNumber;
    private String owner;

    public CarEntity(){
        this.carNumber = null;
        this.owner = null;
    }

    public CarEntity(String carNumber, String owner){
        this.carNumber = carNumber;
        this.owner = owner;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarEntity carEntity = (CarEntity) o;
        return Objects.equals(carNumber, carEntity.carNumber) &&
                Objects.equals(owner, carEntity.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carNumber, owner);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("\"carNumber\" : \"").append(carNumber).append("\",\n");
        sb.append("\"owner\" : \"").append(owner).append("\"\n");
        return sb.toString();
    }

}
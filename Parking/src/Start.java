import java.util.Scanner;

import domain.Car;
import domain.CarOwner;
import domain.Parking;
import domain.ParkingPlace;
import service.CarService;
import service.impl.CarServiceImpl;

public class Start {

    private static CarService service = new CarServiceImpl(); // todo move to Factory approach

    // НЕ ПИШИТЕ ВЕСЬ КОД ВЗАИМОДЕЙСТВИЯ С ПОЛЬЗОВАТЕЛЕМ СЮДА (я так сделала для демо),
    // продумайте как сделать иначе
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите номер машины");
        Car car = new Car();
        if (scanner.hasNext()) {
            car.setPlate(scanner.next());
        }

        System.out.println("Введите цвет машины");
        if (scanner.hasNext()) {
            car.setColor(scanner.next());
        }

        CarOwner owner = new CarOwner();
        System.out.println("Введите owner name");
        if (scanner.hasNext()) {
            owner.setCarOwnerName(scanner.next());
        }

        System.out.println("Введите owner second name");
        if (scanner.hasNext()) {
            owner.setCarOwnerSecondName(scanner.next());
        }

        System.out.println("Введите nickname");
        if (scanner.hasNext()) {
            owner.setUsername(scanner.next());
        }

        System.out.println("Введите password");
        if (scanner.hasNext()) {
            owner.setPassword(scanner.next());
        }

        ParkingPlace place = new ParkingPlace();
        System.out.println("Введите place name");
        if (scanner.hasNext()) {
            place.setPlaceNumber(scanner.next());
        }


        Parking parking = new Parking();
        System.out.println("Введите parking name");
        if (scanner.hasNext()) {
            parking.setParkingName(scanner.next());
        }

        System.out.println(car);
        System.out.println(owner);
    }
}

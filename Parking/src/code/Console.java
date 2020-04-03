package code;
import code.dao.CarEntity;
import code.domain.Parking;
import code.service.ParkingService;
import code.service.UserService;

import code.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Console {

    static final Scanner sc = new Scanner(System.in);

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws Exception {
        Console console = new Console();
        console.menu();
    }

    public void menu() throws Exception {
        logger.info("Starting program");
        String menu = "\"Menu\""
                + "\n------\n"
                + "1. Show all parking places;\n"
                +"2. Log in;\n"
                + "3. Exit.\n"
                + "------\n"
                +"Choose menu item\n>> ";
        System.out.print(menu);
        int choose;
        while (!sc.hasNextInt() || (choose = sc.nextInt()) < 1 || choose > 3){ //check for proper input
            wrongInput();
            System.out.print(">> ");
        }
            switch (choose) {
                case 1:
                    User guest = new User();
                    showParkingPlaces(guest);
                    break;
                case 2: {
                    logIn();
                    break;
                }
                case 3: {
                        System.exit(0);
                        break;
                    }
                }
    }

    public void showParkingPlaces(User user) throws Exception {
        try {
            System.out.print("Showing all parking places\n");
            ParkingService parkingService = new ParkingService();
            ArrayList<String> parkingList = parkingService.getParkingStrings();
            for (int i = 0; i < parkingList.size(); i++) {
                System.out.println((i + 1) + ". " + parkingList.get(i));
            }
            User emptyUser = new User();
            if (user.equals(emptyUser)) menu();
            inSystemWork(user);
        } catch (IOException e){
            logger.error("Can't show parking places");
        }
    }

    public void manageParkingPlaces(){
// to do
    }

    public User inputUser(){
        User login = new User();
        String username, password;
        System.out.print("\nEnter username\n>> ");
        username = sc.next();
        System.out.print("\nEnter password\n>> ");
        password = sc.next();
        login.setUsername(username);
        login.setPassword(password);
        return login;
    }

    public boolean usersChecker(User login, ArrayList<User> usersList){
        boolean check = true;
        for (int i=0; i<usersList.size(); i++) {
            if (login.equals(usersList.get(i))) {
                System.out.println("Please, enter another username or password ");
                check = false;
            }
        }
        return check;
    }

    public User registration(User login, ArrayList<User> usersList, UserService users) throws IOException {
        do {
            login = inputUser();
        }
        while (!usersChecker(login, usersList));
        System.out.print("Pls, add car\nEnter car number:\n");
        String carNumber = sc.next();
        CarEntity[] car = new CarEntity[1];
        CarEntity newCar = new CarEntity(carNumber, login.getUsername());
        car[0]=newCar;
        login.setCars(car);
        users.addUser(login);
        return login;
    }

    public void logIn() throws Exception {
        System.out.println("Entering in system");

        UserService users = new UserService();
        ArrayList<User> usersList = users.getUser();

        System.out.println("1: Sign in\n2: Sign on");
        int choose;
        while (!sc.hasNextInt() || (choose = sc.nextInt()) < 1 || choose > 2){ //check for proper input
            wrongInput();
        }

        User login = new User();
        boolean entrance = false;

        if (choose == 1) {
            do {
                login = inputUser();
                int help = 0;
                for (int i=0; i<usersList.size(); i++) {
                    if (login.equals(usersList.get(i))) {
                        entrance = true;
                    } else help++;
                }
                if (help == usersList.size()) {
                    System.out.println("There is not such user. \n" +
                            "1: Try again \n" +
                            "2: Sign on \n");
                    int choose1;
                    while (!sc.hasNextInt() || (choose1 = sc.nextInt()) < 1 || choose1 > 2) { //check for proper input
                        wrongInput();
                    }
                    if (choose1 == 2) {
                        login = registration(login, usersList, users);
                        entrance = true;
                    }
                }
            } while (!entrance);

        }
        if (choose == 2) {
            login = registration(login, usersList, users);
            entrance = true;
        }

        if (entrance) inSystemWork(login);
    }

    public void inSystemWork(User login) throws Exception {
        UserService users = new UserService();

        ParkingService parkingService = new ParkingService();
        ArrayList<Parking> parkingList = parkingService.getParking();

        ArrayList<User> usersList = users.getUser();
        ArrayList<String> userArray = users.getUserStrings();

        for (int i = 0; i < usersList.size(); i++) {
            if (login.equals(usersList.get(i))) {
                login.setAdmin(usersList.get(i).getIsAdmin());
                login.setCars(usersList.get(i).getCars());
            }
        }


        System.out.println("You entered in system as " + login.getUsername());

        if (login.getIsAdmin()) {
            logInOutAsAdmin(login);
        }

        System.out.println("\nWhat you want to do: \n" +
                "1: Show parking places\n" +
                "2. Set car\n" +
                "3. Remove car\n" +
                "4. See where my car\n" +
                "5. Exit\n");
        int choose;
        while (!sc.hasNextInt() || (choose = sc.nextInt()) < 1 || choose > 5) { //check for proper input
            wrongInput();
        }
        switch (choose){
            case 1:
                showParkingPlaces(login);
                break;
            case 2:
                System.out.print("Choose your car :\n");
                for (int i=0; i<login.getCars().length; i++) {
                    System.out.print(login.getCars()[i].getCarNumber() + "\n");
                }
                System.out.print("Write your car number : ");
                String carNumber = sc.next();
                for (int i=0; i<login.getCars().length; i++) {
                    if (carNumber.equals(login.getCars()[i].getCarNumber())) {

                        for (int k = 0; k < parkingList.size(); k++) {
                            for (int j = 0; j < parkingList.get(k).getParkingPlaces().length; j++) {
                                if (login.getCars()[i].getCarNumber().equals(parkingList.get(k).getParkingPlaces()[j].getSettedCar().getCarNumber())) {
                                    System.out.print("This car already on parking\n");
                                    inSystemWork(login);
                                }
                            }
                        }

                        System.out.print("Choose parking place (write it id): ");
                        String id = sc.next();

                        inSystemWork(parkingService.SetCar(login, carNumber, id));
                    }
                }
                System.out.print("Wrong car number!\nPlease, try again\n\n");
                inSystemWork(login);

                break;
            case 3:
                System.out.print("You can remove this cars: \n");
                for (int i=0; i<login.getCars().length; i++) {
                        for (int k = 0; k < parkingList.size(); k++) {
                            for (int j = 0; j < parkingList.get(k).getParkingPlaces().length; j++) {
                                if (login.getCars()[i].getCarNumber().equals(parkingList.get(k).getParkingPlaces()[j].getSettedCar().getCarNumber())) {
                                    System.out.print(login.getCars()[i].getCarNumber()+"\n");
                                }
                            }
                        }
                    }
                System.out.print("Write number of car, that need to be removed : ");
                String carNumber1 = sc.next();

                for (int i=0; i<login.getCars().length; i++) {
                    if (carNumber1.equals(login.getCars()[i].getCarNumber())) {
                        inSystemWork(parkingService.RemoveCar(login,carNumber1));
                    }
                }
                System.out.print("Wrong number of car, try again\n");
                inSystemWork(login);
                break;
            case 4:
                System.out.print("Your cars :\n");
                for (int i=0; i<login.getCars().length; i++) {
                    System.out.print(login.getCars()[i].getCarNumber());
                    for (int k = 0; k < parkingList.size(); k++) {
                        for (int j = 0; j < parkingList.get(k).getParkingPlaces().length; j++) {
                            if (login.getCars()[i].getCarNumber().equals(parkingList.get(k).getParkingPlaces()[j].getSettedCar().getCarNumber())) {
                                System.out.print(" ---- this car stay at place : \n" + parkingList.get(k).getParkingPlaces()[j].toString() + "\n");
                            }
                        }
                    }
                    System.out.print("\n");
                }
                inSystemWork(login);
                break;
            case 5:
                System.exit(0);
                break;
            default:
                logger.error("Invalid value");
        }
    }

    public void logInOutAsAdmin(User login){
        System.out.println("There will be something soon :)");
        System.exit(0);
    }

    private static void wrongInput(){
        sc.nextLine();
        logger.error("Wrong input\n");
    }

    private static void pause(){
        System.out.print("Enter any symbol to continue...");
        sc.next();
        System.out.println();
    }
}
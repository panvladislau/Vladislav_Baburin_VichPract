package code;
import code.service.UserService;
import code.service.ParkingPlaceService;

import code.domain.User;
import code.domain.ParkingPlace;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Console {

    static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Console console = new Console(); //variables creating
        console.menu();
    }

    public void menu() throws Exception {
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
        System.out.print("Showing all parking places\n");
        ParkingPlaceService parkingPlace = new ParkingPlaceService();
        ArrayList<String> parkingPlaceList = parkingPlace.getParkingPlaceStrings();
        //System.out.print("\nUsers:\n------\n\n");
        for (int i = 0; i < parkingPlaceList.size(); i++) {
            System.out.println((i + 1) + ". " + parkingPlaceList.get(i));
        }
        User emptyUser = new User();
        if (user.equals(emptyUser)) menu();
        inSystemWork(user);
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

    public boolean usersChecker(User login, ArrayList<String> usersList, UserService users){
        boolean check = true;
        for (String s : usersList) {
            if (login.toString().equals(s)) {
                System.out.println("Please, enter another username or password ");
                check = false;
            }
        }
        return check;
    }

    public User registration(User login, ArrayList<String> usersList, UserService users) throws IOException {
        do {
            login = inputUser();
        }
        while (!usersChecker(login, usersList, users));
        users.addUser(login);
        return login;
    }

    public void logIn() throws Exception {
        System.out.println("Entering in system");

        UserService users = new UserService();
        ArrayList<String> usersList = users.getUserStrings();

        System.out.println("1: Sign in\n 2: Sign on");
        int choose;
        while (!sc.hasNextInt() || (choose = sc.nextInt()) < 1 || choose > 2){ //check for proper input
            wrongInput();
            System.out.print(">> ");
        }

        User login = new User();
        boolean entrance = false;

        if (choose == 1) {
            do {
                login = inputUser();
                int help = 0;
                for (String s : usersList) {
                    if (login.toString().equals(s)) {
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
                        System.out.print(">> ");
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

        ParkingPlaceService parkingPlaceService = new ParkingPlaceService();
        ArrayList<ParkingPlace> parkingPlace = parkingPlaceService.getParkingPlace();

        ArrayList<User> usersList = users.getUser();
        ArrayList<String> arrayList = users.getUserStrings();

        for (int i = 0; i < usersList.size(); i++) {
            if (login.toString().equals(arrayList.get(i))) {
                login.setAdmin(usersList.get(i).getIsAdmin());
            }
        }
        System.out.println("You entered in system as \n" + login.toString());

        if (login.getIsAdmin()) {
            logInOutAsAdmin(login);
        }

        System.out.println("What you want to do: \n" +
                "1: Show parking places\n" +
                "2. Set car\n" +
                "3. Remove car\n" +
                "4. See where my car\n" +
                "5. Exit\n");
        int choose;
        while (!sc.hasNextInt() || (choose = sc.nextInt()) < 1 || choose > 5) { //check for proper input
            wrongInput();
            System.out.print(">> ");
        }
        switch (choose){
            case 1:
                showParkingPlaces(login);
                break;
            case 2:
                System.out.print("Write your car number:");
                String carNumber = sc.next();
                System.out.print("Choose parking place (its number before name): ");
                int place = sc.nextInt();

                if (place <1 || place >parkingPlace.size()) {
                    System.out.print("There is no such place!\n ");
                    inSystemWork(login);
                }

                parkingPlaceService.setCar(parkingPlace.get(place-1),carNumber, login);
                inSystemWork(login);
                break;
            case 3:
                System.out.print("Choose parking place: ");
                int place1 = sc.nextInt();

                if (place1 <1 || place1 >parkingPlace.size()) {
                    System.out.print("There is no such place!\n ");
                    inSystemWork(login);
                }

                    parkingPlaceService.cleanPlace(parkingPlace.get(place1 - 1), login);
                    inSystemWork(login);
                break;
            case 4:
                int help = 0;
                for (int i = 0; i < parkingPlace.size(); i++) {
                    help ++;
                    if (login.getUsername().equals(parkingPlace.get(i).getCurrentUser())) {
                        System.out.print("Your car there : \n" + (i + 1) + ". " + parkingPlace.get(i).toString() );
                    }
                }
                if (help==parkingPlace.size())  System.out.print("There is no you car placed already\n");
                inSystemWork(login);
                break;
            case 5:
                System.exit(0);
                break;
        }
    }

    public void logInOutAsAdmin(User login){
        System.out.println("You entered in system as admin\n" +
                "There will be something soon :)");
        System.exit(0);
    }

    private static void wrongInput(){
        sc.nextLine();
        System.out.println("Input error\n");
    }

    private static void pause(){
        System.out.print("Enter any symbol to continue...");
        sc.next();
        System.out.println();
    }
}
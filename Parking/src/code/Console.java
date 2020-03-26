package code;
import code.service.UserService;

import code.domain.User;
import java.util.ArrayList;
import java.util.Scanner;

public class Console {
    public static void main(String[] args) throws Exception {
        UserService users = new UserService();
        Scanner sc = new Scanner(System.in);
        ArrayList<String> usersList = users.getUserStrings();
        System.out.print("\nUsers:\n------\n\n");
        for (int i = 0; i < usersList.size(); i++) {
            System.out.println((i + 1) + ". " + usersList.get(i));
        }
        User login = new User();
        String username, password;
        System.out.print("\nEnter username\n>> ");
        username = sc.next();
        System.out.print("\nEnter password\n>> ");
        password = sc.next();
        login.setUsername(username);
        login.setPassword(password);
        System.out.print(login);
        /*        users.addUser(login);*/

        for (int i = 0; i < usersList.size(); i++) {
            if (login.toString().equals(usersList.get(i))) {
                System.out.print("You entered in system");
            }
        }

        //users.addUser(login);


    }
}
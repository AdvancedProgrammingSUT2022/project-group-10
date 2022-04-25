package View;

import Controller.UserController;
import Model.Game;
import Model.User;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {


    public static Scanner scanner = new Scanner(System.in);

    public static void run() {
        String command;
        ArrayList<User> players;
        if (LoginMenu.run(scanner) == 1) return;
        while (true) {
            Matcher matcher;
            command = scanner.nextLine();
            if (Commands.getMatcher(command, Commands.EXIT_MENU) != null) {
                if (LoginMenu.run(scanner) == 1) break;
            }
            if (Commands.getMatcher(command, Commands.CURRENT_MENU) != null) {
                System.out.println("Main Menu");
            } else if (Commands.getMatcher(command, Commands.LOGOUT) != null) {
                System.out.println(UserController.logUserOut());
                if (LoginMenu.run(scanner) == 1) break;
            } else if ((matcher = Commands.getMatcher(command, Commands.ENTER_MENU)) != null) {
                if (matcher.group("menuName").equals("Profile Menu")) {
                    System.out.println("entered Profile menu!");
                    ProfileMenu.run(scanner);
                } else if (matcher.group("menuName").equals("Game Menu"))
                    System.out.println("use \"play game\" command");
                else if (matcher.group("menuName").equals("Login Menu")) System.out.println("use \"logout\" command");
                else System.out.println("menu navigation is not possible");
            } else if (Commands.getMatcher(command, Commands.START_GAME) != null) {
                if ((players = UserController.startGame(Commands.getUsernames(command))) != null) {
                    System.out.println("game started");
                    //TODO navigate to game menu
                    GameMenu.startGame(players);
                } else System.out.println("some usernames aren't valid");
            } else {
                System.out.println("invalid command");
            }
        }
    }
}

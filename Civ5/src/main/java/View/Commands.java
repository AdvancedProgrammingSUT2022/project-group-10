package View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Commands {
    REGISTER1("^user create (--username|-u) (?<username>\\S+) (--nickname|-n) (?<nickname>\\S+) (--password|-p) (?<password>\\S+)$"),
    REGISTER2("^user create (--username|-u) (?<username>\\S+) (--password|-p) (?<password>\\S+) (--nickname|-n) (?<nickname>\\S+)$"),
    REGISTER3("^user create (--nickname|-n) (?<nickname>\\S+) (--username|-u) (?<username>\\S+) (--password|-p) (?<password>\\S+)$"),
    REGISTER4("^user create (--nickname|-n) (?<nickname>\\S+) (--password|-p) (?<password>\\S+) (--username|-u) (?<username>\\S+)$"),
    REGISTER5("^user create (--password|-p) (?<password>\\S+) (--username|-u) (?<username>\\S+) (--nickname|-n) (?<nickname>\\S+)$"),
    REGISTER6("^user create (--password|-p) (?<password>\\S+) (--nickname|-n) (?<nickname>\\S+) (--username|-u) (?<username>\\S+)$"),
    LOGIN1("^user login (--username|-u) (?<username>\\S+) (--password|-p) (?<password>\\S+)$"),
    LOGIN2("^user login (--password|-p) (?<password>\\S+) (--username|-u) (?<username>\\S+)$"),
    LOGOUT("^user logout$"),
    START_GAME("^play game ((--player|-p)\\d+ \\S+)+$"),
    ENTER_MENU("^menu enter (?<menuName>Login Menu|Main Menu|Game Menu|Profile Menu)$"),
    EXIT_MENU("^menu exit$"),
    CURRENT_MENU("^menu show-current$"),
    CHANGE_NICKNAME("^profile change (--nickname|-n) (?<nickname>\\S+)$"),
    CHANGE_PASSWORD1("^profile change (--password|-p) (--current|-c) (?<currentPassword>\\S+) (--new|-n) (?<newPassword>\\S+)$"),
    CHANGE_PASSWORD2("^profile change (--password|-p) (--new|-n) (?<newPassword>\\S+) (--current|-c) (?<currentPassword>\\S+)$"),
    CHANGE_PASSWORD3("^profile change (--current|-c) (?<currentPassword>\\S+) (--password|-p) (--new|-n) (?<newPassword>\\S+)$"),
    CHANGE_PASSWORD4("^profile change (--current|-c) (?<currentPassword>\\S+) (--new|-n) (?<newPassword>\\S+) (--password|-p)$"),
    CHANGE_PASSWORD5("^profile change (--new|-n) (?<newPassword>\\S+) (--password|-p) (--current|-c) (?<currentPassword>\\S+)$"),
    CHANGE_PASSWORD6("^profile change (--new|-n) (?<newPassword>\\S+) (--current|-c) (?<currentPassword>\\S+) (--password|-p)$");



    private String regex;

    Commands(String regex) {
        this.regex = regex;
    }

    public static ArrayList<String> getUsernames(String command) {
        HashMap<Integer, String> players = new HashMap<>();
        String[] strings = command.split("-");
        strings = Arrays.copyOfRange(strings, 1, strings.length);
        for (String string : strings) {
            if (string.startsWith("-player")) {
                players.put(Integer.parseInt(string.substring(7, 8)), string.substring(9));
            }else {
                players.put(Integer.parseInt(string.substring(1, 2)), string.substring(3));
            }
        }
        ArrayList<String> usernames = new ArrayList<>();
        for (Map.Entry<Integer, String> e : players.entrySet()) {
            usernames.add(e.getKey() - 1, e.getValue());
        }
        return usernames;
    }

    public static Matcher getMatcher(String input, Commands command) {
        Matcher matcher = Pattern.compile(command.regex, Pattern.CASE_INSENSITIVE).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
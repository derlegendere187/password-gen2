package de.petersohnm.passwordgenv2;


import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int pwlength = 0;                   // -l
        boolean specialcharacters = true;   // -s
        boolean uppercaseAllowed = true;    // -u
        boolean numbersAllowed = true;      // -n


        if (args.length == 0) {
            printHelpMenu();
            System.exit(1);
        }

        for (String arg : args) {
            if (arg.equals("--secret")) {
                Secret.secret();
                System.exit(0);
            }
        }

        checkInvalidCharacter(args);
        searchHelp(args);
        searchVersion(args);
        pwlength = lengthCheck(args);

        for (String arg : args) {
            if (arg.equals("-s")) {
                specialcharacters = false;
            }
            if (arg.equals("-u")) {
                uppercaseAllowed = false;
            }
            if (arg.equals("-n")) {
                numbersAllowed = false;
            }
        }
        System.out.println("---INFORMATION---");
        System.out.println("length: " + pwlength);
        System.out.println("special chars?: " + specialcharacters);
        System.out.println("uppercases?: " + uppercaseAllowed);
        System.out.println("numbers?: " + numbersAllowed);
        System.out.println("----------------");
        String finalPassword = PasswordGenerator.generatePassword(pwlength, specialcharacters, uppercaseAllowed, numbersAllowed);

        System.out.println("Your password:\n" + finalPassword);
    }

    /**
     * Checks everything, that has to do with the number input (integer, length, ...)
     * @param args The arguments provided by user input.
     * @return The checked password length.
     */
    public static int lengthCheck(String[] args) {
        int pwlength = 0;
        boolean lengthIsNotValid = true;

        for (int pos = 0; pos < args.length; pos++) {
            if (args[pos].equals("-l")) {
                try {
                    pwlength = Integer.parseInt(args[pos + 1]);
                    if (pwlength <= 0) {
                        System.out.println("The password length must be at least 1.");
                        System.exit(1);
                    }
                } catch (Exception e) {
                    System.out.println("Please enter a valid integer for the password length.");
                    System.out.println("Use '--help' or '-h' to see helpful information.");
                    System.exit(1);
                }
                lengthIsNotValid = false;
            }
        }
        if (lengthIsNotValid) {
            System.exit(1);
        }
        return pwlength;
    }

    /**
     * Checks if there is a invalid input by the user.
     * @param args The arguments provided by user input.
     */
    public static void checkInvalidCharacter(String[] args) {
        String[] allowedSymbols = new String[]{"-l", "-s", "-u", "-n", "--help", "-help", "-h", "?", "-?", "/?", "-version", "--version", "-v", "--secret"};
        for (String arg : args) {
            // Checks if the argument contains only digits.
            // \d = one digit (0–9), + = one or more.
            // So \d+ matches multi-digit numbers (e.g., 10, 25, 100).
            if (!Arrays.stream(allowedSymbols).toList().contains(arg) && !arg.matches("-l") && !arg.matches("\\d+")) {
                System.out.println("Unknown argument: " + arg);
                System.out.println("Use '--help' or '-h' to see helpful information.");
                System.exit(1);
            }
        }
    }

    /**
     * Checks if any of the help symbols are used.
     * @param args The arguments provided by user input.
     */
    public static void searchHelp(String[] args) {
        String[] helpSymbols = new String[]{"--help", "-help", "-h", "?", "-?", "/?"};

        for (String arg : args) {

            List<String> helpSymbolsList = Arrays.stream(helpSymbols).toList();
            // Prints the help menu, if no args found
            if (helpSymbolsList.contains(arg) || arg.isEmpty()) {
                printHelpMenu();
                System.exit(0);
            }
        }
    }

    /**
     * Checks if user wants to see the version of the programm.
     * @param args The arguments provided by user input.
     */
    public static void searchVersion(String[] args) {
        String[] versionSymbols = new String[]{"-version", "--version", "-v"};

        for (String arg : args) {
            // Prints the help menu, if no args found
            if (Arrays.stream(versionSymbols).toList().contains(arg)) {
                System.out.println("Current version: Password Generator 1.4.0");
                System.exit(0);
            }
        }
    }

    /**
     * Prints the help menu.
     */
    public static void printHelpMenu() {
        String helptext =
                """
                Here you can find useful information.
                    <> - necessary
                    [] - optional
                
                    usage: pwgen <-l <int>>         // password length
                                 [-s]               // special characters
                                 [-u]               // uppercases
                                 [-n]               // numbers
                
                    explanation: '-l'   Sets the length of the password.
                                 '-s'   When used, special characters will NOT be implemented in the password.
                                 '-u'   When used, uppercase letters will NOT be used in the password.
                                 '-n'   When used, numbers will NOT be used in the password.
                

                    To see the version of this program, use '--version' or '-v'.

                    '--help' or '-h' to see helpful information.
                """;
        System.out.println(helptext);
    }
}
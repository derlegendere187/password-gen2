package de.petersohnm.passwordgenv2;


import java.util.Arrays;
import java.util.Random;
import java.util.random.RandomGenerator;

public class Main {
    public static void main(String[] args) {
        String[] allowedSymbols = new String[]{"-l", "-s", "-u", "-n", "--help", "-h", "?", "-?", "/?"};
        int pwlength = 0;                   // -l
        boolean specialcharacters = true;   // -s
        boolean uppercaseAllowed = true;    // -u
        boolean numbersAllowed = true;      // -n

        if (args.length == 0) {
            printHelpMenu();
            System.exit(0);
        }

        searchHelp(args);
        pwlength = lengthCheck(args);

        for (String arg : args) {

            if (arg.equals("-s")) {
                specialcharacters = false;
//                System.out.println(specialcharacters);
            }
            if (arg.equals("-u")) {
                uppercaseAllowed = false;
//                System.out.println(uppercaseAllowed);
            }
            if (arg.equals("-n")) {
                numbersAllowed = false;
            }
        }
        System.out.println("---DEBUG AREA---");
        System.out.println("length:" + pwlength);
        System.out.println("special chars:" + specialcharacters);
        System.out.println("uppercases:" + uppercaseAllowed);
        System.out.println("numbers" + numbersAllowed);
        System.out.println("----------------");
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
                        System.exit(0);
                    }
                } catch (Exception e) {
                    System.out.println("Please enter a valid integer for the password length.");
                }
                lengthIsNotValid = false;
            }
        }
        if (lengthIsNotValid) {
            System.exit(0);
        }
        return pwlength;
    }

    /**
     * Checks if any of the help symbols are used.
     * @param args The arguments provided by user input.
     */
    public static void searchHelp(String[] args) {
        String[] helpSymbols = new String[]{"--help", "-h", "?", "-?", "/?"};

        for (String arg : args) {
            // Prints the help menu, if no args found
            if (Arrays.stream(helpSymbols).toList().contains(arg) || arg.isEmpty()) {
                printHelpMenu();
                break;
            } else {
                return;
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
                
                    usage: Main.java <-l <int>>         // password length
                                     [-s]               // special characters
                                     [-u]               // uppercases
                                     [-n]               // numbers
                
                    explanation: '-l'   Sets the length of the password.
                                 '-s'   When used, special characters will NOT be implemented in the password.
                                 '-u'   When used, uppercase letters will NOT be used in the password.
                                 '-n'   When used, numbers will NOT be used in the password.
                
                    '--help' or '-h' to see helpful information.
                """;
        System.out.println(helptext);
    }
}

// TODO: no command found -> help
//
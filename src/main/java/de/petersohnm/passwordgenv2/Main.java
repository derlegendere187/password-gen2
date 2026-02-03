package de.petersohnm.passwordgenv2;


import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] allowedSymbols = new String[]{"-l", "-s", "-u", "-n", "--help", "-help", "-h", "?", "-?", "/?", "-version", "--version", "-v"};
        int pwlength = 0;                   // -l
        boolean specialcharacters = true;   // -s
        boolean uppercaseAllowed = true;    // -u
        boolean numbersAllowed = true;      // -n

        System.out.println(System.getProperty("user.name"));
        System.exit(0);


        if (args.length == 0) {
            printHelpMenu();
            System.exit(0);
        }

        for (String arg : args) {
            // Hier wird überprüft, ob die Argumente gültig sind. Als gültig zählt alles, was in allowedSymbols (und eine eine Zahl für die Passwortlänge) eingetragen ist.
            // Was genau macht aber das "\\d+"? -> Es überprüft, ob der String nur aus Ziffern besteht (also eine Zahl ist).
            // Würde man das '+' weglassen, würde es nur einzelne Ziffern (0-9) erkennen, aber keine mehrstelligen Zahlen (z.B. 10, 25, 100).
            // Das ist wichtig, weil die Passwortlänge eine mehrstellige Zahl sein kann.
            if (!Arrays.stream(allowedSymbols).toList().contains(arg) && !arg.matches("-l") && !arg.matches("\\d+")) {
                System.out.println("Unknown argument: " + arg);
                System.out.println("Use '--help' or '-h' to see helpful information.");
                System.exit(0);
            }
        }

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
                        System.exit(0);
                    }
                } catch (Exception e) {
                    System.out.println("Please enter a valid integer for the password length.");
                    System.out.println("Use '--help' or '-h' to see helpful information.");
                    System.exit(0);
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
        String[] helpSymbols = new String[]{"--help", "-help", "-h", "?", "-?", "/?"};

        for (String arg : args) {
            // Prints the help menu, if no args found
            if (Arrays.stream(helpSymbols).toList().contains(arg) || arg.isEmpty()) {
                printHelpMenu();
                System.exit(0);
            }
        }
        return;
    }

    public static void searchVersion(String[] args) {
        String[] versionSymbols = new String[]{"-version", "--version", "-v"};

        for (String arg : args) {
            // Prints the help menu, if no args found
            if (Arrays.stream(versionSymbols).toList().contains(arg)) {
                System.out.println("Current version: Password Generator 1.3.2");
                System.exit(0);
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

// TODO: no command found -> help
// TODO: eval
// TODO: React, view, vive?
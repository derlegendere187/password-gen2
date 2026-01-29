package de.petersohnm.passwordgenv2;


import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int pwlength = 0;                   // -l
        boolean specialcharacters = true;   // -s
        boolean uppercaseAllowed = true;    // -u
        boolean numbersAllowed = true;      // -n

        searchHelp(args);

        lengthCheck(args);

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
    }

    public static void lengthCheck(String[] args) {
        int pwlength = 0;
        boolean lengthIsNotValid = true;

        for (int pos = 0; pos < args.length; pos++) {
            if (args[pos].equals("-l")) {
                try {
                    pwlength = Integer.parseInt(args[pos + 1]);
                    if (pwlength <= 0) {
                        System.out.println("Die Länge des Passwortes muss mindestens eine größe von 1 haben.");
                        System.exit(0);
                    }
                } catch (Exception e) {
                    System.out.println("Bitte gib eine gültige Ganzzahl für das Passwort ein!");
                }
                lengthIsNotValid = false;
            }
        }
        if (lengthIsNotValid) {
            System.exit(0);
        }
    }

    public static void searchHelp(String[] args) {
        String[] helpSymbols = new String[]{"--help", "-h", "?", "-?", "/?"};

        for (String arg : args) {
            if (Arrays.stream(helpSymbols).toList().contains(arg) || arg.isBlank()) {
                printHelpMenu();
                break;
            } else {
                return;
            }
        }
    }

    public static void printHelpMenu() {
        String helptext =
                """
                Here you can find useful information.
                    <> - necessary
                    [] - optional
                
                    usage: Main.java <-l <int>>
                                     [-s <boolean>]
                                     [-u <boolean>]
                                     [-n <boolean>]
                
                    explanation: '-l'   Sets the length of the password.
                                 '-s'   If true, special characters will be used in the password.
                                 '-u'   If true, uppercase letters will be used in the password.
                                 '-n'   If true, numbers will be used in the password.
                
                    '--help' or '-h' to see helpful information.
                """;
        System.out.println(helptext);
    }
}

// TODO: default no command found -> help
// TODO: boolean umdrehen, wenn gesetzt wurde
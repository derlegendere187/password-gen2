package de.petersohnm.passwordgenv2;


public class Main {
    public static void main(String[] args) {
        int pwlength;                       // -l
        boolean specialcharacters = true;   // -s
        boolean uppercaseAllowed = true;    // -u

        searchHelp(args);

        for (int pos = 0; pos < args.length; pos++) {

            if (args[pos].equals("-l")) {
                pwlength = Integer.parseInt(args[pos + 1]);
                System.out.println(pwlength);
            }
            if (args[pos].equals("-s")) {
                specialcharacters = Boolean.parseBoolean(args[pos + 1]);
                System.out.println(specialcharacters);
            }
            if (args[pos].equals("-u")) {
                uppercaseAllowed = Boolean.parseBoolean(args[pos + 1]);
                System.out.println(uppercaseAllowed);
            }
        }
    }

    public static void searchHelp(String[] args) {
        for (String arg : args) {
            String helptext =
                    """
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
            if (arg.equals("--help") || arg.equals("-h")) {
                System.out.println(helptext);
                break;
            } else {
                return;
            }
        }
    }
}
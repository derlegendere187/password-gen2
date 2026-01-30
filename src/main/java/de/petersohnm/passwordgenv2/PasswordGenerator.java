package de.petersohnm.passwordgenv2;

import java.security.SecureRandom;

/**
 * In this class is the method defined to generate the password.
 */
public class PasswordGenerator {
    private static final SecureRandom GENERATOR = new SecureRandom();


    /**
     * Method to generate the users final password.
     *
     * @param pwlength          Sets the length of the password.
     * @param specialcharacters If true, special characters will be included in the password.
     * @param uppercaseAllowed  If true, uppercase letters will be included in the password.
     * @param numbersAllowed    If true, numbers will be included in the password.
     * @return The final password.
     */
    public static String generatePassword(int pwlength, boolean specialcharacters, boolean uppercaseAllowed, boolean numbersAllowed) {
        final String LOWER = "abcdefghijklmnopqrstuvwxyz";
        final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String NUMBERS = "01234567890123456789";
        final String SPECIAL = "!@#$%&*+-_?!@#$%&*+-_?";


        // StringBuilder ist, besonders für lange Strings, sehr effizient und ressourcenschonend
        StringBuilder acceptedValues = new StringBuilder(LOWER); // lower per default immer mit dabei

        if (specialcharacters) { acceptedValues.append(SPECIAL); };
        if (uppercaseAllowed) { acceptedValues.append(UPPER); };
        if (numbersAllowed) { acceptedValues.append(NUMBERS); };

        // Hier werden die acceptedValues zu einem String zusammengeführt.
        String finalValues = acceptedValues.toString();

        // Baut das Passwort nach und nach weiter aus, indem es random Zahlen generiert.
        // Diese Zahlen nehmen dann die passenden Werte aus dem finalValues-String heraus.
        // Diese Werte werden dann zu result appended, was dazu führt, dass ein String aus random Values generiert wird.
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < pwlength; i++) {
            int randomChar = GENERATOR.nextInt(finalValues.length());
            password.append(finalValues.charAt(randomChar));
        }
        return password.toString();
    }
}

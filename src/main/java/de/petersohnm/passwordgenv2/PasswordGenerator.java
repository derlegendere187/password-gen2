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


        String acceptedValues = LOWER; // 'lower' is initialized by default

        if (specialcharacters) { acceptedValues += SPECIAL; }
        if (uppercaseAllowed) { acceptedValues += UPPER; }
        if (numbersAllowed) { acceptedValues += NUMBERS; }

        String finalValues = acceptedValues;

        // Builds the password step by step by generating random numbers.
        // These numbers pick the corresponding characters from the finalValues string.
        // Those characters are appended to 'password', creating a string of random values.
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < pwlength; i++) {
            int randomChar = GENERATOR.nextInt(finalValues.length());
            password.append(finalValues.charAt(randomChar));
        }
        return password.toString();
    }
}

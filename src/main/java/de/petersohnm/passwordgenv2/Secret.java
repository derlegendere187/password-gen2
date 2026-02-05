package de.petersohnm.passwordgenv2;

import java.util.concurrent.TimeUnit;

public class Secret {

    public static void secret() throws InterruptedException {
        System.out.println("Uh, you found our secret.");
        System.out.println("Well, you get nothing for it.");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("\nJust kidding, here you go:");
        System.out.println("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        System.out.println("Have fun with it!\n");
        System.exit(0);
    }
}

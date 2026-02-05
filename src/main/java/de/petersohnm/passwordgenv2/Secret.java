package de.petersohnm.passwordgenv2;

import java.util.concurrent.TimeUnit;

public class Secret {

    public static void secret() {
        System.out.println("Oha, du hast ein tolles Secret gefunden.");
        System.out.println("Dafür bekommst du nichts.");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Spaß, hier hast du was:");
        System.out.println("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        System.out.println("Viel Spaß damit!");
        System.exit(0);
    }
}

package application.util;

import java.io.IOException;

public class Spause {
    public static void pausa() {
        System.out.println("\nPressione ENTER para continuar...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
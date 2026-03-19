package view.util;

import java.io.IOException;

public class Spause {
    public static void pausa() {
        System.out.println(" Pressione ENTER para continuar...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
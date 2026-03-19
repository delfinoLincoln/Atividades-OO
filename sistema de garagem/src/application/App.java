package application;

import controller.GaragemController;
import view.util.ClearScreen;

public class App {
    public static void main(String[] args) {
        ClearScreen.cls();
        
        GaragemController controller = new GaragemController();
        controller.iniciar();
    }
}

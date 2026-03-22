package sistema_biblioteca.src.application;

import sistema_biblioteca.src.controller.BibliotecaController;

public class Main {
    public static void main(String[] args) {
        BibliotecaController controller = new BibliotecaController();
        controller.iniciar();
    }
}
package application.util;

public class ClearScreen {
    public static void cls() {
        System.out.print("\033[H\033[2J"); // \033[H move o cursor para o canto superior esquerdo, \033[2J limpa a tela
        System.out.flush(); // Garante que a saída seja exibida imediatamente
    }
}

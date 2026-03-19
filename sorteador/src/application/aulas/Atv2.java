package application.aulas;

//import java.util.Arrays;
//import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import util.ClearScreen;

public class Atv2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int vet[] = new int[30];

        ClearScreen.cls();

        for (int i = 0; i < vet.length; i++) {
            vet[i] = random.nextInt(100) + 1;
        }

        System.out.print("Original: ");
        System.out.print("[");
        for (int i = 0; i < vet.length; i++) {
            System.out.print(vet[i]);
            if (i < vet.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        System.out.println();
        System.out.print("Deseja ordenar por ordem crescente ou decrescente(c/d)? ");
        char resp = sc.next().toLowerCase().charAt(0);

        System.out.println();
        switch (resp) {

            //Bubble sort crescente
            case 'c':
                /*
                 * Arrays.sort(vet);
                 * System.out.println("Crescente:" + Arrays.toString(vet));
                 * break;
                 */

                for (int i = 0; i < (vet.length - 1); i++) {
                    for (int j = 0; j < (vet.length - 1); j++) {
                        if (vet[j] > vet[j + 1]) {
                            int aux = vet[j];
                            vet[j] = vet[j + 1];
                            vet[j + 1] = aux;
                        }
                    }
                }
                System.out.print("Crescente: [");
                for (int i = 0; i < vet.length; i++) {
                    System.out.print(vet[i]);
                    if (i < vet.length - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println("]");
                break;

            //Bubble sort decrescente
            case 'd':
                /*
                 * Arrays.sort(vet, Collections.reverseOrder());
                 * System.out.println("Decrescente: " + Arrays.toString(vet));
                 * break;
                 */

                for (int i = 0; i < vet.length - 1; i++) {
                    for (int j = 0; j < vet.length - i - 1; j++) {
                        if (vet[j] < vet[j + 1]) {
                            int aux = vet[j];
                            vet[j] = vet[j + 1];
                            vet[j + 1] = aux;
                        }
                    }
                }
                System.out.print("Decrescente: [");
                for (int i = 0; i < vet.length; i++) {
                    System.out.print(vet[i]);
                    if (i < vet.length - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println("]");
                break;
        }
        sc.close();
    }
}
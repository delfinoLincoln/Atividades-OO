package application;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import util.ClearScreen;

public class Atv1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random ran = new Random();

        int sortedNumber = ran.nextInt(100) + 1;
        int attempts = 0;
        
        System.out.println("----Guessing game----");
        System.out.println("Guess the number from 1 to 100");
        
        ClearScreen.cls();
        while (true) {
            try {
                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();
                attempts++;

                if (guess < 1 || guess > 100) {
                    System.out.println("Enter a number from 0 to 100! ");
                    continue;
                }

                if (guess == sortedNumber) {
                    System.out.println();
                    System.out.println("NICE! YOU ACCEPTED THE WINNING NUMBER!!!");
                    System.out.println("Number attempts: " + attempts);
                    System.out.println("The winning number is " + sortedNumber);
                    break;
                }
                else if (guess < sortedNumber) {
                    System.out.println("Lower guess!");
                }
                else {
                    System.out.println("Bigger guess!");
                }

            } 
            catch(InputMismatchException e) {
                System.out.println("Error: " + e.getMessage());
                sc.next();
            }
        }
        sc.close();
    }
}
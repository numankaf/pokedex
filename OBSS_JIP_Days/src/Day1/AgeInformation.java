package Day1;

import java.util.Scanner;

public class AgeInformation {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your age : ");
        int age = scanner.nextInt();
        if (age <= 0) {
            System.out.println("You age cannot be negative , idiot!");
        } else if (age >= 60) {
            System.out.println("You are about to die!!");
        } else if (age >= 40) {
            System.out.println("You are pretty old , aren't you?");
        } else if (age >= 20) {
            System.out.println("You are in your best years!");
        } else {
            System.out.println("What are you, a baby or a teenager?");
        }
    }
}

package Day2;
import java.util.Scanner;

public class RandomNumberGenerator {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Starting the game...");
            int n = (int) (Math.random() * (100 - 1)) + 1;
            int steps = 5;
            while (steps != 0) {
                System.out.printf("Enter your guess (Remaining step count : %d) : ", steps);
                int g = scanner.nextInt();
                if (g == n) {
                    System.out.print("You got it correct! Well done!");
                    System.exit(0);
                } else if (g > n) {
                    System.out.print("The number is lower than your guess! Try again\n");
                } else {
                    System.out.print("The number is higher than your guess! Try again\n");
                }
                steps--;
            }
            System.out.printf("It is over! It was %d \n", n);
        }
    }
}

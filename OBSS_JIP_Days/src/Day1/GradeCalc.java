package Day1;

import java.util.Scanner;

public class GradeCalc {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many exams do you have? : ");
        int i = 1;
        int c = scanner.nextInt();
        double sum = 0;
        while (i <= c) {
            System.out.printf("Enter your grade of exam %d : ", i);
            double g = scanner.nextDouble();
            while (g < 0 || g > 100) {
                System.out.printf("Invalid grade. Enter your grade of exam %d again : ", i);
                g = scanner.nextDouble();
            }
            sum += g;
            i++;
        }
        double avg = sum / c;
        System.out.printf("Your average grade is %f", avg);
    }
}

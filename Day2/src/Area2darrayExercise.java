import java.util.Scanner;

public class Area2darrayExercise {
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of cities : ");
        int totalArea=0;
        int size =  scanner.nextInt();
        int arr[][] = new int[size][2];
        for (int i = 1; i <size+1 ; i++) {
            System.out.printf("Enter the %d . city code : ",i);
            int code =  scanner.nextInt();
            System.out.printf("Enter the %d . city area : ",i);
            int area = scanner.nextInt();
            arr[i-1][0] = code;
            arr[i-1][1] = area;
            totalArea+=area;
        }
        for (int i = 0; i <size ; i++) {
            System.out.printf("City 1: Code = %d  , Area : %d \n", arr[i][0], arr[i][1]);
        }
        System.out.printf("Total area is %d", totalArea);
    }
}

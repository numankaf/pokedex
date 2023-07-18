package Day2;
public class ArrayExercise {
    static void reverse(int arr[], int size){
        int rarr[] = new int[size];
        for (int i = 0; i <size ; i++) {
            rarr[size-i-1] = arr[i];
        }
        for (int i = 0; i <size ; i++) {
            System.out.print(rarr[i]+" ");
        }
        System.out.println();

    }

    public static void main(String args[]){
        int size = (int) (Math.random() * (50 - 1)) + 1;
        int arr[] = new int[size];
        System.out.println("Normal array");
        for (int i = 0; i <size ; i++) {
            arr[i] = (int) (Math.random() * (100 - 1)) + 1;
            System.out.print(arr[i]+" ");
        }
        System.out.println();

        System.out.println("Reversed array");
        reverse(arr , size);

    }
}

package Day3.ExceptionExample;

public class Main {
    static void myFunction(int n) throws MyException {
        if (n % 2 == 0) {
            throw new MyException();
        }
        System.out.println("The number is odd");
    }

    public static void main(String[] args) throws MyException {

        myFunction(54);

    }
}

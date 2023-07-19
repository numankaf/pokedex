package Day3.HandlingExceptions;

public class MyUncheckedException extends RuntimeException{
    MyUncheckedException(){
        System.out.println("This is 1/0 error which is ArithmeticException");
    }
}

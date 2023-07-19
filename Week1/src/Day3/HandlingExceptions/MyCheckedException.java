package Day3.HandlingExceptions;

public class MyCheckedException extends Exception{
    MyCheckedException(){
        System.out.println("This is a checked exception but not we wanted");
    }
}

package Day3.HandlingExceptions;

public class Executor {
    static void exec() throws MyCheckedException{
        try {
            Divider.divide();
        }
        catch (Exception e){
            throw new MyUncheckedException();
        }

    }
}

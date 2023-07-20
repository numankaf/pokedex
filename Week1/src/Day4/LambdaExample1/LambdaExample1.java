package Day4.LambdaExample1;

interface Calculator{
    int calculate(int a, int b);
}
public class LambdaExample1 {
    public static void main(String[] args){
        Calculator addition = (a,b)->a+b;
        Calculator substraction = (a,b)->a-b;
        System.out.println(addition.calculate(5,9));
        System.out.println(substraction.calculate(78,45));
    }

}

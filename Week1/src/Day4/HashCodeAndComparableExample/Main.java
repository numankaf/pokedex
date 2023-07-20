package Day4.HashCodeAndComparableExample;

public class Main {
    public static void main(String[] args){
        Student s1 = new Student(1);
        Student s2 = new Student(2);
        Student s3 = new Student(3);
        Student s4  = new Student(1);

        System.out.println(s1.equals(s4));
    }
}

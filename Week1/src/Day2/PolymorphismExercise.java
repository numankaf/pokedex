package Day2;

class Person{
    public void f(){
        System.out.println("I am a person");
    }
}

class Employee extends Person{
    @Override
    public void f() {
        System.out.println("I am a employee");
    }
}


public class PolymorphismExercise {
    static void someMethod(Person person){
        Employee emp = (Employee) person;
        emp.f();
    }

    public static void main(String args[]){
        Person p = new Person();
        Employee e = new Employee();
        p.f();;
        p =e;
        p.f();
        someMethod(p);
    }

}

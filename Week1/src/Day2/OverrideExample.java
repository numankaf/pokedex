package Day2;

class A{
    public void f(){
        System.out.println("A");
    }
}

class B extends A{
    @Override
    public void f(){
        System.out.println("B");
    }
}

public class OverrideExample {
    public static void main(String args[]){
        A a = new A();
        B b = new B();
        a.f();
        b.f();
    }
}

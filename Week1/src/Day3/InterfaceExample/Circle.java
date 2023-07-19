package Day3.InterfaceExample;

public class Circle implements Drawable {
    private int radius;
    Circle(int radius){
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.print("Circle area is : ");
        System.out.print(radius*radius*3.14);
        System.out.println();
    }

    @Override
    public void clear() {
        System.out.println("what to do?");
    }

    @Override
    public void fill() {
        System.out.println("Again what to do ?");

    }
}

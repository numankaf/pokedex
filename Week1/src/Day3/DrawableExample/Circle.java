package Day3.DrawableExample;

public class Circle extends Shape{
    private int radius;
    Circle(String color, int radius){
        super(color);
        this.radius = radius;
    }

    @Override
    double getArea() {
        return radius*radius*Math.PI;
    }

    @Override
    public String getDrawingInfo() {
        return "This is Circle";
    }
}

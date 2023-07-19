package Day3.DrawableExample;

public class Rectangle extends Shape{
    private int width;
    private int height;
    Rectangle(String color, int width, int height){
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    public String getDrawingInfo() {
        return "This is rectangle";
    }

    @Override
    double getArea() {
        return width*height;
    }
}

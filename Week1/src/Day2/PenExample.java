package Day2;

class Circle{
    private int radius;
    private String color;
    Circle(int radius, String color){
        this.color = color;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                ", color='" + color + '\'' +
                '}';
    }
}
class Rectangle{
    private int width;
    private int height;
    private String color;

    Rectangle(int width, int height, String color){
        this.width = width;
        this.height = height;
        this.color = color;
    }
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                ", color='" + color + '\'' +
                '}';
    }
}
class Pen{
    public void drawRectangle(Rectangle r){
        System.out.print("Rectangle area is ");
        System.out.println(r.getWidth()*r.getHeight());
    }
    public void drawCircle(Circle c){
        System.out.print("Circle area is ");
        System.out.println(c.getRadius()*c.getRadius()* 3.14);
    }
    public void changeColorRectangle(String color, Rectangle r){
        r.setColor(color);
    }
    public void changeColorCircle(String color , Circle c){
        c.setColor(color);
    }

}

public class PenExample {
    public static void main(String args[]){
        Circle c = new Circle(4, "red");
        Rectangle r = new Rectangle(4, 5, "blue");
        Pen p = new Pen();
        p.drawCircle(c);
        p.drawRectangle(r);
        System.out.println(c);
        p.changeColorCircle("cyan", c);
        System.out.println(c);
        System.out.println(r);
        p.changeColorRectangle("black", r);
        System.out.println(r);
    }
}

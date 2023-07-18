package Day2;

class Shape{
    private String color;
    Shape(String color){
        this.color = color;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
class NewRectangle extends Shape{
    private int width;
    private int height;
    NewRectangle(int width, int height, String color){
        super(color);
        this.width = width;
        this.height = height;
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

}
class NewCircle extends Shape{
    private int radius;
    NewCircle(int radius,String color){
        super(color);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

}
class NewPen{
    public void drawShape(Shape shape){
        if(shape instanceof NewRectangle){
            NewRectangle r = (NewRectangle) shape;
            System.out.print("Rectangle area is ");
            System.out.println(r.getWidth()*r.getHeight());
        }
        if(shape instanceof NewCircle){
            NewCircle c = (NewCircle) shape;
            System.out.print("Circle area is ");
            System.out.println(c.getRadius()*c.getRadius()* 3.14);
        }
    }
}

public class PenRevisited {
    public static void main(String args[]){
        Shape c = new NewCircle(4, "red");
        Shape r = new NewRectangle(4, 5, "blue");
        NewPen p = new NewPen();
        p.drawShape(c);
        p.drawShape(r);
    }

}

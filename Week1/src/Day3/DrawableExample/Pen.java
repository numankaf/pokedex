package Day3.DrawableExample;

public class Pen {
    public void draw(Drawable d){
        System.out.println(d.getDrawingInfo());
    }
    public void printArea(Shape s){
        System.out.printf("Area is %f \n", s.getArea());
    }
}

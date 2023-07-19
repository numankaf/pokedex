package Day3.DrawableExample;

public class Main {
    public static void main(String[] args){
        Circle c = new Circle("Red", 5);
        Rectangle r = new Rectangle("Blue",4,9);
        Pen p = new Pen();
        p.draw(c);
        p.draw(r);
        p.printArea(c);
        p.printArea(r);
    }
}

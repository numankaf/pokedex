package Day3.DrawableExample;

public abstract class Shape implements Drawable {
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

    abstract double getArea();
}

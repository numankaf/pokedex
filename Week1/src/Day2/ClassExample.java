package Day2;

class Car{
    private String color;
    private int capacity;

    Car(){
        this.color= "Blue";
        this.capacity = 5;
    }
    Car(String color, int capacity){
        this.color= color;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}

public class ClassExample {
    public static void main(String args[]){
        Car car = new Car();
        Car car2 = new Car("Red", 4);
        System.out.println(car);
        System.out.println(car2);
    }
}

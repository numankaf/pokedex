package Day5.ShoppingCart;

public class Computer extends CartItem{
    private String brand;

    Computer(String name, double price, int quantity,String brand) {
        super(name, price, quantity);
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", quantity=" + getQuantity() +
                "brand='" + brand + '\'' +
                '}';
    }
}

package Day5.ShoppingCart;

public class MobilePhone extends CartItem{
    MobilePhone(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public String toString() {
        return "MobilePhone{" +
                "name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", quantity=" + getQuantity() +
                '}';

    }
}

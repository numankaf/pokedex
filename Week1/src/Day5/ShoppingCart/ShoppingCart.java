package Day5.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart<T extends CartItem> {
    private List<T> cartContent;

    ShoppingCart(){
        this.cartContent = new ArrayList<>();
    }

    public void addItem(T item){
        this.cartContent.add(item);
    }
    public double calculateTotalPrice(){
        double total =0;
        for(T it:cartContent){
            total +=it.getPrice();
        }
        return total;
    }
    public void displayCartContents(){
        System.out.println("Cart Content: ");
        for(T t:cartContent){
            System.out.println(t);
        }
    }
    public T compareItems(T item1, T item2){
        if(item1.getPrice()<item2.getPrice()){
            return item1;
        }else{
            return item2;
        }
    }



}

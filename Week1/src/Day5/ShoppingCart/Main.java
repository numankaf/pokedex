package Day5.ShoppingCart;

public class Main {
    public static void main(String[] args){
        ShoppingCart<CartItem> shoppingCart = new ShoppingCart<>();
        Computer c1  = new Computer("Computer 1",10000,15,"Asus");
        Computer c2  = new Computer("Computer 2",30000,45,"HP");
        Computer c3  = new Computer("Computer 3",20000,25,"Apple");
        Computer c4  = new Computer("Computer 4",14000,55,"Dell");
        MobilePhone mp1 = new MobilePhone("Mobile Phone 1 ", 4000,5);
        MobilePhone mp2 = new MobilePhone("Mobile Phone 2 ", 6000,4);
        shoppingCart.addItem(c1);
        shoppingCart.addItem(c2);
        shoppingCart.addItem(mp1);
        shoppingCart.displayCartContents();
        System.out.println(shoppingCart.calculateTotalPrice());
        shoppingCart.addItem(c3);
        shoppingCart.addItem(mp2);
        shoppingCart.addItem(c4);
        shoppingCart.displayCartContents();
        System.out.println(shoppingCart.calculateTotalPrice());

        System.out.println(shoppingCart.compareItems(c2,mp1));

    }
}

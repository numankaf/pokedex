package Day5.InnerClassExample;

public class Main {
    public static void main(String[] args){
        Book.Chapter chapter1  = new Book().new Chapter();
        Book book = new Book();
        Book.Chapter chapter2 = book.new Chapter();

        Book.Publisher publisher1 = new Book.Publisher();

    }
}
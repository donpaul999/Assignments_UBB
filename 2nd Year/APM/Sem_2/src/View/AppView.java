package View;
import Controller.*;
import Repository.ArrayRepository;
import Repository.IRepo;
import Model.*;

public class AppView {
    public static void main(String[] arg) {
        IRepo repo = new ArrayRepository(5);
        Controller controller = new Controller(repo);

        Apple a1 = new Apple(2, 3);
        Book b1 = new Book(...);
        Cake c1 = new Cake(…);

        try {
            controller.addElement(a1);
            controller.addElement(b1);
            controller.addElement(c1);
        }
        catch (MyExc exception) {
            System.out.println(exception);
        }
        String[] array = controller.filterItems(200);
        for(String el: array) {
            System.out.println(el);
        }
     }
}

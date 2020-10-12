package View;
import Controller.*;
import Exceptions.MyException;
import Repository.Repository;
import Repository.IRepo;
import Model.*;

public class AppView {
    public static void main(String[] arg) {
        IRepo repo = new Repository(5);
        Controller controller = new Controller(repo);

        Fish f1 = new Fish("Green", 5);
        Fish f2 = new Fish("Blue", 2);
        Turtle t1 = new Turtle("Jack", 10);
        Turtle t2 = new Turtle("Jack2", 0);

        try {
            controller.addElement(f1);
            controller.addElement(f2);
            controller.addElement(t1);
            controller.addElement(t2);
        }
        catch (MyException exception) {
            System.out.println(exception);
        }
        String[] array = controller.filterItems(1);
        for(String el: array) {
            System.out.println(el);
        }
    }
}
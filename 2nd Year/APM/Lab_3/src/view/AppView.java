package view;

import controller.Controller;
import exceptions.MyException;
import repository.IRepo;
import repository.Repository;

import java.util.Scanner;
public class AppView {
    Controller controller;

    public void printMenu() {
        System.out.println("****************\n");
        System.out.println("Example_1 = (int x;(x=17;print(x)))\n");
        System.out.println("Example_2 = (int x;(x=3+5*7;print(x)))\n");
        System.out.println("****************\n");
    }

    public AppView() {
        IRepo repository = new Repository();
        this.controller = new Controller(repository);
        printMenu();
        try {
            controller.example();
            controller.allSteps();
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }
}
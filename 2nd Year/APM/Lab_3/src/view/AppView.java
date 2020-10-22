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
        /*TODO add examples
        
         */

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
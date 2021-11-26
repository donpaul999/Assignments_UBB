import domain.Grammar;
import domain.Parser;

import java.io.BufferedReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        Grammar g = new Grammar();
        g.readGrammar("g1.txt");
        g.checkCFG();
        Parser parser = new Parser(g);

        boolean exit = false;
        while (!exit) {
            displayUI();
            int option = scan.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Input production:");
                    scan.nextLine();
                    String input = scan.nextLine();
                    System.out.println(parser.closureLR(input));
                    break;
                case 2:
                    System.out.println("Input symbol:");
                    scan.nextLine();
                    input = scan.nextLine();
                    System.out.println(parser.goTo(parser.closureLR("A -> .B"), input));
                    break;
                case 3:
                    System.out.println(parser.colCanLR());
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Wrong input!");
                    break;
            }
        }
    }

    private static void displayUI() {
        System.out.println("1. Closure");
        System.out.println("2. Go To");
        System.out.println("3. Canonical Collection");
        System.out.println("0. Exit");
        System.out.println("Select option:");
    }
}

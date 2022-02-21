import adt.Pair;
import domain.Grammar;
import domain.Parser;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        Grammar g = new Grammar();
        g.readGrammar("g0.txt");
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
                    System.out.println(parser.goTo(parser.closureLR("S' -> .S"), input));
                    break;
                case 3:
                    System.out.println(parser.colCanLR());
                    break;
                case 4:
                    System.out.println(parser.createLRTable(parser.colCanLR()));
                    break;
                case 5:
                    System.out.println("Input word:");
                    scan.nextLine();
                    input = scan.nextLine();
                    List<Map<List<String>, List<List<String>>>> colCanLr = parser.colCanLR();
                    HashMap<Integer, Pair<String, HashMap<String, Integer>>> lrTable=parser.createLRTable(colCanLr);
                    try {
                        List<String> wordResult = parser.parsingAlg(lrTable, colCanLr, input);

                        if (wordResult == null) {
                            System.out.println("Error!");
                        } else {
                            System.out.println("Success!");
                            System.out.println(wordResult);
                        }
                    }
                    catch (Exception e) {
                        System.out.println("Error!");
                    }
                    break;
                case 6 : {
                    System.out.println("Give input:");
                    input = scan.next();
                    List<Map<List<String>, List<List<String>>>> colCanLr2 = parser.colCanLR();
                    HashMap<Integer, Pair<String, HashMap<String, Integer>>> lrTable2 = parser.createLRTable(colCanLr2);
                    try {
                        List<String> wordResult = parser.parsingAlg(lrTable2, colCanLr2, input);

                        if (wordResult == null) {
                            System.out.println("Error!");
                        } else {
                            parser.createTable(wordResult);
                        }
                    }
                    catch (Exception e) {
                        System.out.println("Error!");
                    }
                    break;
                }
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
        System.out.println("4. Display LR table");
        System.out.println("5. Parse word");
        System.out.println("6. Create table");
        System.out.println("0. Exit");
        System.out.println("Select option:");
    }
}

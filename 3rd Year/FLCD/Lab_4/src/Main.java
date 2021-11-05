import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        FiniteAutomata finiteAutomata = new FiniteAutomata("FA.in");
        boolean exit = false;
        Scanner keyboard = new Scanner(System.in);
        while(!exit) {
            printUI();
            int option = keyboard.nextInt();
            switch (option) {
                case 1:
                    System.out.println(finiteAutomata.getStates());
                    break;
                case 2:
                    System.out.println(finiteAutomata.getAlphabet());
                    break;
                case 3:
                    System.out.println(finiteAutomata.getTransitions());
                    break;
                case 4:
                    System.out.println(finiteAutomata.getFinalStates());
                    break;
                case 5:
                    if (finiteAutomata.isDFA()) {
                        System.out.println("It is a DFA");
                    } else {
                        System.out.println("It isn't a DFA");
                    }
                    break;
                case 6:
                    System.out.println("Write sequence:");
                    keyboard.nextLine();
                    String sequence = keyboard.nextLine();
                    if (finiteAutomata.isAcceptedByFA(sequence)) {
                        System.out.println("Accepted!");
                    } else {
                        System.out.println("Rejected!");
                    }
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Wrong option!");
                    break;
            }
        }
    }

    private static void printUI() {
        System.out.println("1. Print set of states");
        System.out.println("2. Print alphabet");
        System.out.println("3. Print transitions");
        System.out.println("4. Print the set of final states");
        System.out.println("5. Check if it is a DFA");
        System.out.println("6. Check a sequence");
        System.out.println("0. Exit");
        System.out.println("Choose option:");
    }
}

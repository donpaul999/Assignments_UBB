import domain.Grammar;

public class Main {
    public static void main(String[] args) throws Exception {
        Grammar g = new Grammar();
        g.readGrammar("g1.txt");
        g.checkCFG();
    }
}

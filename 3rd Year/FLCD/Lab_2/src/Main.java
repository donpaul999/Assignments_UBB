
public class Main {
    public static void main(String[] args) {
        SymbolTable symbolTable = new SymbolTable(8);

        String[] symbols = {"a1", "b2", "c3", "d4", "a1"};

        System.out.println("Add --------------");
        for (int i = 0; i < symbols.length; ++i) {
            System.out.println(symbolTable.addSymbol(symbols[i]));
        }

        System.out.println("Contains --------------");
        for (int i = 0; i < symbols.length; ++i) {
            System.out.println(symbolTable.containsSymbol(symbols[i]));
        }

        System.out.println("Remove --------------");
        for (int i = 0; i < symbols.length; ++i) {
            System.out.println(symbolTable.removeSymbol(symbols[i]));
        }
    }
}

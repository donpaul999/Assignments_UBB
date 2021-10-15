
public class Main {
    public static void main(String[] args) {
        SymbolTable symbolTable = new SymbolTable(8);

        String[] symbols = {"a1", "1a", "b2", "c3", "d4", "a1"};

        System.out.println("Add --------------");
        for (int i = 0; i < symbols.length; ++i) {
            System.out.println(symbolTable.addSymbol(symbols[i]));
        }

        System.out.println("\nContains --------------");
        for (int i = 0; i < symbols.length; ++i) {
            System.out.println(symbolTable.containsSymbol(symbols[i]));
        }

        symbols = new String[]{"a1", "1a", "b2", "c3", "d4", "a1", "b5"};
        System.out.println("\nPosition --------------");
        for (int i = 0; i < symbols.length; ++i) {
            System.out.println(symbolTable.getPosition(symbols[i]));
        }

        System.out.println("\nRemove --------------");
        for (int i = 0; i < symbols.length; ++i) {
            System.out.println(symbolTable.removeSymbol(symbols[i]));
        }
    }
}

import java.util.ArrayList;

public class SymbolTable {
    private ArrayList<ArrayList<String>> symbols;
    private int length;

    public SymbolTable(int length) {
        this.length = length;
        this.symbols = new ArrayList<>();
        for (int i = 0; i < length; ++i) {
            this.symbols.add(new ArrayList<>());
        }
    }

    public boolean addSymbol(String symbol) {
        int hashValue = hash(symbol);

        if(symbols.get(hashValue).contains(symbol)) {
            return false;
        }

        symbols.get(hashValue).add(symbol);
        return true;
    }

    private int hash(String symbol) {
        return symbol.codePoints().sum() % length;
    }

    public boolean containsSymbol(String symbol) {
        return symbols.get(hash(symbol)).contains(symbol);
    }

    public boolean removeSymbol(String symbol) {
        int hashValue = hash(symbol);

        if(!symbols.get(hashValue).contains(symbol)) {
            return false;
        }

        symbols.get(hashValue).remove(symbol);
        return true;
    }
}

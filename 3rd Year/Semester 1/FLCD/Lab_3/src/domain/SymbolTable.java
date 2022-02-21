package domain;

import adt.Pair;

import java.util.ArrayList;

public class SymbolTable {
    private ArrayList<ArrayList<String>> symbols;
    private int size;

    public SymbolTable(int size) {
        this.size = size;
        this.symbols = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            this.symbols.add(new ArrayList<>());
        }
    }

    public boolean addSymbol(String symbol) {
        int hashValue = hash(symbol);

        if (symbols.get(hashValue).contains(symbol)) {
            return false;
        }

        symbols.get(hashValue).add(symbol);
        return true;
    }

    private int hash(String symbol) {
        return symbol.codePoints().sum() % size;
    }

    public boolean containsSymbol(String symbol) {
        return symbols.get(hash(symbol)).contains(symbol);
    }

    public boolean removeSymbol(String symbol) {
        int hashValue = hash(symbol);

        if (!symbols.get(hashValue).contains(symbol)) {
            return false;
        }

        symbols.get(hashValue).remove(symbol);
        return true;
    }

    public Pair getPosition(String symbol) {
        if (!containsSymbol(symbol))
            return null;

        return new Pair(hash(symbol), symbols.get(hash(symbol)).indexOf(symbol));
    }

    @Override
    public String toString() {
        return "SymbolTable{" +
                "items=" + symbols +
                ", size=" + size +
                '}';
    }
}

package domain;
import adt.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Scanner {
    SymbolTable symbolTable;
    ArrayList<String> tokens;
    ArrayList<Pair<String, Integer>> pif;

    public Scanner(SymbolTable symbolTable, String tokenFile) {
        this.symbolTable = symbolTable;
        pif = new ArrayList<>();
        readTokens(tokenFile);
    }

    private void readTokens(String tokenFile) {
        tokens = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(tokenFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                tokens.add(line.strip());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void scan(String filePath) throws IOException {
        boolean correct = true;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null && correct) {
                ArrayList<String> receivedTokens = tokenGenerator(line.strip());
                for (int i = 0; i < receivedTokens.size(); ++i){
                    if (receivedTokens.get(i).equals(" ") || receivedTokens.get(i).isEmpty()) {
                        continue;
                    }
                    if (isOperator(receivedTokens.get(i)) || isSeparator(receivedTokens.get(i)) || isReservedWord(receivedTokens.get(i))) {
                        pif.add(new Pair(receivedTokens.get(i), -1));
                    } else if(isIdentifier(receivedTokens.get(i))) {
                        symbolTable.addSymbol(receivedTokens.get(i));
                        pif.add(new Pair("id", symbolTable.getPosition(receivedTokens.get(i))));
                    } else if(isConstant(receivedTokens.get(i))) {
                        System.out.println(receivedTokens.get(i));
                        symbolTable.addSymbol(receivedTokens.get(i));
                        pif.add(new Pair("constant", symbolTable.getPosition(receivedTokens.get(i))));
                    } else {
                        System.err.println("Lexical error! Undefined token " + receivedTokens.get(i) + " on line " + lineNumber);
                        correct = false;
                    }
                }
                lineNumber += 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (correct) {
            System.out.println("All good!");
            writePif();
            writeST();
        }
    }

    private boolean isConstant(String s) {
        String onlyDigits = "[0-9]+";
        String isString = "([\"'])(?:(?=(\\\\?))\\2.)*?\\1";
        return s.matches(onlyDigits) || s.matches(isString);
    }

    private boolean isIdentifier(String s) {
        String pattern = "^[_a-z]\\w*$";
        return s.matches(pattern);
    }

    private void writePif() throws IOException {
        FileWriter outputfile = new FileWriter("PIF.out");

        for (int i = 0; i < pif.size(); ++i) {
            outputfile.write(pif.get(i) + "\n");
        }
        outputfile.flush();
        outputfile.close();
    }

    private void writeST() throws IOException {
        FileWriter outputfile = new FileWriter("ST.out");

        outputfile.write(String.valueOf(symbolTable));

        outputfile.flush();
        outputfile.close();
    }

    private boolean isOperator(String token) {
        String[] operators = {"+", "-", "*", "/", "=", "$"};
        return Arrays.asList(operators).contains(token);
    }

    private boolean isSeparator(String token) {
        String[] separators = {"(", ")", "[", "]", "{", "}", ":", ";", ",", "", " ", "\t", "\n"};
        return Arrays.asList(separators).contains(token);
    }

    private boolean isReservedWord(String token) {
        String[] words = {"START", "END", "and", "array_numbers", "bigger_than", "equals", "for", "greater_or_equal", "if", "let", "not_equals",
        "number", "or", "print", "read", "smaller_or_equal", "smaller_than", "space", "string", "while"};
        return Arrays.asList(words).contains(token);
    }

    private ArrayList<String> tokenGenerator(String line) {
        ArrayList<String> tokens = new ArrayList<>();
        int i = 0, j = 0;
        while (j < line.length()) {
            if (line.charAt(j) == '"') {
                tokens.add(line.substring(i, j).strip());
                i = j;
                j += 1;
                if (line.substring(i + 1).indexOf('"') == -1) {
                    j = line.length() - 1;
                } else {
                    while (line.charAt(j) != '"' && j < line.length()) {
                        j += 1;
                    }
                    j += 1;
                }
            }
            if (isOperator(String.valueOf(line.charAt(j))) || isSeparator(String.valueOf(line.charAt(j)))) {
                tokens.add(line.substring(i, j).strip());
                tokens.add(line.substring(j, j).strip());
                i = j + 1;
            }
            j += 1;
        }

        return tokens;
    }
}

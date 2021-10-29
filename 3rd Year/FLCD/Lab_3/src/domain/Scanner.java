package domain;
import adt.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scanner {
    SymbolTable symbolTable;
    ArrayList<String> tokens;
    ArrayList<Pair<String, Integer>> pif;

    private final String ONLY_DIGITS_REGEX = "^([0-9]+)(?=[\\n:;+\\-*/%, ()}{\\]\\[\"]|$)";
    private final String STRING_CONSTANT_REGEX = "^\"[0-9a-zA-Z]+\"";
    private final String IDENTIFIER_REGEX = "^[A-Za-z][A-Za-z0-9]*";
    private final String OPERATOR_REGEX = "^=|\\+|-|\\*|/|%|\\$|=";
    private final String SEPARATOR_REGEX = "^[\\n:;,()}{\\]\\[\"]";
    private final String RESERVED_WORDS_REGEX = "^\\b(START|END|and|array_numbers|bigger_than|equals|for|greater_or_equal|if|let|not_equals|number|or|print|read|smaller_or_equal|smaller_than|space|string|while)\\b";

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
            boolean found;
            while ((line = br.readLine()) != null && correct) {
                while (!line.isEmpty()) {
                    found = false;
                    line = line.strip();
                    Pattern pattern = Pattern.compile(RESERVED_WORDS_REGEX);
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find() && matcher.start() == 0) {
                        pif.add(new Pair(matcher.group(), -1));
                        line = line.substring(matcher.end());
                        found = true;
                    }

                    pattern = Pattern.compile(SEPARATOR_REGEX);
                    matcher = pattern.matcher(line);
                    if (matcher.find() && matcher.start() == 0) {
                        pif.add(new Pair(matcher.group(), -1));
                        line = line.substring(matcher.end());
                        found = true;
                    }

                    pattern = Pattern.compile(OPERATOR_REGEX);
                    matcher = pattern.matcher(line);
                    if (matcher.find() && matcher.start() == 0) {
                        pif.add(new Pair(matcher.group(), -1));
                        line = line.substring(matcher.end());
                        found = true;
                    }

                    pattern = Pattern.compile(IDENTIFIER_REGEX);
                    matcher = pattern.matcher(line);
                    if (matcher.find() && matcher.start() == 0) {
                        symbolTable.addSymbol(matcher.group());
                        pif.add(new Pair("id", symbolTable.getPosition(matcher.group())));
                        line = line.substring(matcher.end());
                        found = true;
                    }

                    pattern = Pattern.compile(ONLY_DIGITS_REGEX);
                    matcher = pattern.matcher(line);
                    if (matcher.find() && matcher.start() == 0) {
                        symbolTable.addSymbol(matcher.group());
                        pif.add(new Pair("constant", symbolTable.getPosition(matcher.group())));
                        line = line.substring(matcher.end());
                        found = true;
                    }

                    pattern = Pattern.compile(STRING_CONSTANT_REGEX);
                    matcher = pattern.matcher(line);
                    if (matcher.find() && matcher.start() == 0) {
                        symbolTable.addSymbol(matcher.group());
                        System.out.println("const" + matcher.group());
                        pif.add(new Pair("constant", symbolTable.getPosition(matcher.group())));
                        line = line.substring(matcher.end());
                        found = true;
                    }

                    if (!found) {
                        System.err.println("Lexical error! Undefined token on line " + lineNumber);
                        correct = false;
                        break;
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
        String onlyDigits = "^[+-]?[0-9]+";
        String isString = "\"[0-9a-zA-Z]*\"";
        return s.matches(onlyDigits) || s.matches(isString);
    }

    private boolean isIdentifier(String s) {
        String pattern = "^[A-Za-z]*[A-Za-z0-9]";
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
        String[] operators = {"+", "-", "*", "/", "%", "=", "$"};
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
        String[] arrTokens = line.split(" ");
        for (int i = 0; i < arrTokens.length; ++i) {
            String word = arrTokens[i];
            ArrayList<String> lastTokens = new ArrayList<>();
            int startPos = 0, endPos;
            if (word.isEmpty() || word.equals(" "))
                continue;

            while (startPos < word.length()) {
                if (isSeparator(String.valueOf(word.charAt(startPos)))) {
                    tokens.add(String.valueOf(word.charAt(startPos)));
                } else {
                    break;
                }
                startPos += 1;
            }

            endPos = word.length() - 1;
            while (endPos > 0 && word.length() > 1) {
                if (isSeparator(String.valueOf(word.charAt(endPos)))) {
                    lastTokens.add(String.valueOf(word.charAt(endPos)));
                } else {
                    break;
                }
                endPos -= 1;
            }

            tokens.add(word.substring(startPos, endPos + 1));
            for (int j = lastTokens.size() - 1; j >= 0; j --) {
                tokens.add(lastTokens.get(j));
            }
        }
        System.out.println(tokens);
        return tokens;
    }
}

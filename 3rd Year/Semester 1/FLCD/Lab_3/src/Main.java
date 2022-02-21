import domain.Scanner;
import domain.SymbolTable;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        SymbolTable symbolTable = new SymbolTable(8);
        Scanner scanner = new Scanner(symbolTable, "Token.in");

        scanner.scan("p1.txt");
        //genFAIdentifiers();
        //genFAConstants();
    }

    private static void genFAConstants() throws IOException {
        String fileIdentifier = "finite-automata-constants.in";
        FileWriter outputfile = new FileWriter(fileIdentifier);

        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        outputfile.write("Q = p i sm sf\n");

        sb.append("E = ");
        for (char i = 'a'; i <= 'z'; i += 1) {
            sb.append(i).append(" ");
        }
        for (char i = 'A'; i <= 'Z'; i += 1) {
            sb.append(i).append(" ");
        }
        for (char i = '0'; i <= '9'; i += 1) {
            sb2.append(i).append(" ");
        }

        outputfile.write(String.valueOf(sb) + String.valueOf(sb2));
        outputfile.write("'");
        outputfile.write("\nq0 = p\n");
        outputfile.write("F = i sf\n");
        outputfile.write("T =\n");

        List<String> alphabet = Arrays.asList(sb.toString().substring(4).split(" "));
        List<String> digits = Arrays.asList(sb2.toString().split(" "));

        for (String d: digits) {
            outputfile.write("(i," + d + ") -> i\n");
            outputfile.write("(p," + d + ") -> i\n");
        }

        for (String a: alphabet) {
            outputfile.write("(p," + a + ") -> sm\n");
        }

        outputfile.write("(p,') -> sm\n");
        for (String a: alphabet) {
            outputfile.write("(sm," + a + ") -> sm\n");
        }
        for (String a: digits) {
            outputfile.write("(sm," + a + ") -> sm\n");
        }
        outputfile.write("(sm,') -> sf\n");
        outputfile.flush();
        outputfile.close();
    }

    private static void genFAIdentifiers() throws IOException {
        String fileIdentifier = "finite-automata-identifiers.in";
        FileWriter outputfile = new FileWriter(fileIdentifier);

        StringBuilder sb = new StringBuilder();
        outputfile.write("Q = p f\n");

        StringBuilder sb2 = new StringBuilder();

        sb.append("E = ");
        for (char i = 'a'; i <= 'z'; i += 1) {
            sb.append(i).append(" ");
        }
        for (char i = 'A'; i <= 'Z'; i += 1) {
            sb.append(i).append(" ");
        }
        for (char i = '0'; i <= '9'; i += 1) {
            sb2.append(i).append(" ");
        }

        outputfile.write(String.valueOf(sb));
        outputfile.write(String.valueOf(sb2));
        outputfile.write("\nq0 = p\n");
        outputfile.write("F = f\n");
        outputfile.write("T =\n");

        List<String> alphabet = Arrays.asList(sb.toString().substring(4).split(" "));
        List<String> digits = Arrays.asList(sb2.toString().split(" "));
        for (String a: alphabet) {
            outputfile.write("(p," + a + ") -> f\n");
            outputfile.write("(f," + a + ") -> f\n");
        }

        for (String a: digits) {
            outputfile.write("(f," + a + ") -> f\n");
        }

        outputfile.flush();
        outputfile.close();
    }
}

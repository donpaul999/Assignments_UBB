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

        //scanner.scan("p2.txt");
        //genFAIdentifiers();
        genFAConstants();
    }

    private static void genFAConstants() throws IOException {
        String fileIdentifier = "finite-automata-constants.in";
        FileWriter outputfile = new FileWriter(fileIdentifier);

        StringBuilder sb = new StringBuilder();
        outputfile.write("Q = i si sm sf\n");

        sb.append("E = ");
        for (char i = 'a'; i <= 'z'; i += 1) {
            sb.append(i).append(" ");
        }
        for (char i = 'A'; i <= 'Z'; i += 1) {
            sb.append(i).append(" ");
        }
        for (char i = '0'; i <= '9'; i += 1) {
            sb.append(i).append(" ");
        }

        outputfile.write(String.valueOf(sb) + ",");
        outputfile.write("\nq0 = i si\n");
        outputfile.write("F = i sf\n");
        outputfile.write("T =\n");

        List<String> alphabet = Arrays.asList(sb.toString().substring(4).split(" "));
        for (String a: alphabet) {
            outputfile.write("(si," + a + ") -> sm\n");
        }
        outputfile.write("(si,') -> sm\n");
        for (String a: alphabet) {
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
        outputfile.write("Q = i f\n");

        sb.append("E = ");
        for (char i = 'a'; i <= 'z'; i += 1) {
            sb.append(i).append(" ");
        }
        for (char i = 'A'; i <= 'Z'; i += 1) {
            sb.append(i).append(" ");
        }
        for (char i = '0'; i <= '9'; i += 1) {
            sb.append(i).append(" ");
        }

        outputfile.write(String.valueOf(sb));
        outputfile.write("\nq0 = i\n");
        outputfile.write("F = f\n");
        outputfile.write("T =\n");

        List<String> alphabet = Arrays.asList(sb.toString().substring(4).split(" "));
        for (String a: alphabet) {
            outputfile.write("(f," + a + ") -> f\n");
        }
        outputfile.flush();
        outputfile.close();
    }
}

package domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Grammar {
    private List<String> nonTerminals; //non-terminals
    private List<String> terminals; // terminals
    private String startingSymbol; //starting symbol
    private Map<List<String>, ArrayList<List<String>>> productionRules; // production rules

    public Grammar() {
        this.productionRules = new HashMap<>();
    }

    public void readGrammar(String fileName) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            nonTerminals = getStatesFromLine(br);
            terminals = getStatesFromLine(br);
            startingSymbol = getStatesFromLine(br).get(0);
            br.readLine();
            while ((line = br.readLine()) != null) {
                List<String> lineList = Arrays.asList(line.split("->"));
                List<String> key = Arrays.asList(lineList.get(0).strip().split(" \\| "));
                ArrayList<List<String>> value = new ArrayList<>();
                String[] token = lineList.get(1).split("\\|");
                for(var str:token){
                    List<String> prod = Arrays.asList(str.strip());
                    value.add(prod);
                }
                productionRules.put(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Non terminals: " + nonTerminals);
        System.out.println("Terminals: " + terminals);
        System.out.println("Starting symbol: " + startingSymbol);
        System.out.println("Productions:");
        productionRules.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        });
    }

    private List<String> getStatesFromLine(BufferedReader br) throws IOException {
        String line = br.readLine();
        List <String> list =  Arrays.asList(line.split(" "));
        return list.subList(2, list.size());
    }

    public void checkCFG() throws Exception {
        if (!nonTerminals.contains(startingSymbol)) {
            throw new Exception("the starting symbol is not in the set of non terminals");
        }

        for(Map.Entry element : productionRules.entrySet()) {
            List<String> key = (List<String>) element.getKey();
            if(key.size() > 1) {
                throw new Exception("One key has more than one element");
            }
            for(String str : key) {
                if(!nonTerminals.contains(str)) {
                    throw new Exception(str + " is not in the set of non terminals");
                }
                if(Collections.frequency(nonTerminals, str) != 1) {
                    throw new Exception(str + " appears multiple times in the non terminals.");
                }
            }
            List<List<String>> value = (List<List<String>>) element.getValue();
            for(List l : value) {
                for(Object o: l) {
                    String str = (String) o;
                    for(var oneStr: str.split(" ")) {
                        if (!nonTerminals.contains(oneStr) && !terminals.contains(oneStr) && !str.equals("E")) { // Check for Epsilon, alphabet
                            throw new Exception(oneStr + " is not found in the set of non terminals or terminals");
                        }
                    }
                }
            }
        }
    }

}
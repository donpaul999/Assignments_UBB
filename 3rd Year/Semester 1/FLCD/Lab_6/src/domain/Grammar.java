package domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Grammar {
    public List<String> nonTerminals; //non-terminals
    public List<String> terminals; // terminals
    public String startingSymbol; //starting symbol
    public Map<List<String>, List<String>> productionRules; // production rules

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
                List<String> value = new ArrayList<>();
                String[] token = lineList.get(1).split("\\|");
                for(var str:token){
//                    List<String> prod = str.strip();
                    value.add(str.strip());
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
        checkCFG();
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

        for (Map.Entry element : productionRules.entrySet()) {
            List<String> key = (List<String>) element.getKey();
            if (key.size() > 1) {
                throw new Exception("One key has more than one element");
            }
            for (String str : key) {
                if (!nonTerminals.contains(str)) {
                    throw new Exception(str + " is not in the set of non terminals");
                }
                if (Collections.frequency(nonTerminals, str) != 1) {
                    throw new Exception(str + " appears multiple times in the non terminals.");
                }
            }
            List<String> value = (List<String>) element.getValue();
            for (Object o : value) {
                String str = (String) o;
                for (var oneStr : str.split(" ")) {
                    if (!nonTerminals.contains(oneStr) && !terminals.contains(oneStr) && !str.equals("E")) { // Check for Epsilon, alphabet
                        throw new Exception(oneStr + " is not found in the set of non terminals or terminals");
                    }
                }
            }
        }
    }

    public Map<List<String>, List<String>> filterP(String nonT) {
        Map<List<String>, List<String>> result = new HashMap<>();
        for (Map.Entry element : productionRules.entrySet()) {
            List<String> key = (List<String>) element.getKey();
            if (key.contains(nonT)) {
                List<String> value = (List<String>) element.getValue();
                result.put(key, value);
            }
        }
        return result;
    }
}
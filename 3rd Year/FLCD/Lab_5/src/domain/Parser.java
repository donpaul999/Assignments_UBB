package domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parser {
    private final Grammar grammar;

    public Parser(Grammar grammar) {
        this.grammar = grammar;
    }

    public Map<List<String>, List<String>> closureLR(String input) {
        Map<List<String>, List<String>> P = new HashMap<>();

        List<String> lineList = Arrays.asList(input.split("->"));
        List<String> key = Arrays.asList(lineList.get(0).strip().split(" \\| "));
        List<String> value = new ArrayList<>();
        String[] token = lineList.get(1).split("\\|");
        for (var str : token) {
            value.add(str.strip());
        }
        P.put(key, value);

        int size = 0, index;
        String nonT;

        while (size < P.size()) {
            size = P.size();
            Map<List<String>, List<String>> filteredP = new HashMap<>(P);

            for (Map.Entry element : filteredP.entrySet()) {
                value = (List<String>) element.getValue();
                for (String s : value) {
                    index = s.indexOf('.');
                    if (index != -1 && index < s.length() - 1) {
                        nonT = s.substring(index + 1);
                        Map<List<String>, List<String>> filteredB = grammar.filterP(nonT);
                        for (Map.Entry elementB : filteredB.entrySet()) {
                            List<String> keyB = (List<String>) elementB.getKey();
                            List<String> valueB = (List<String>) elementB.getValue();
                            if (!P.containsKey(keyB)) {
                                P.put(keyB, valueB.stream().map(x -> "." + x).collect(Collectors.toList()));
                            }
                        }
                    }
                }
            }
        }

        return P;
    }

    public Map<List<String>, List<String>> goTo(Map<List<String>, List<String>> productions, String symbol) {
        Map<List<String>, List<String>> nestedMap = new HashMap<>();

        for (Map.Entry element : productions.entrySet()) {
            List<String> value = (List<String>) element.getValue();
            List<String> key = (List<String>) element.getKey();
            int index = value.indexOf("." + symbol);
            if (index != -1) {
                value.set(index, symbol + ".");
                Map<List<String>, List<String>> closure = closureLR(String.join(" | ", key) + " -> " + String.join(" | ", value));
                nestedMap.putAll(closure);
            }
        }

        return nestedMap;
    }

    public List<Map<List<String>, List<String>>> colCanLR() {
        List<Map<List<String>, List<String>>> result = new ArrayList<>();

        result.add(closureLR("A -> .B")); // change this

        boolean ok = true;

        while(ok) {
            ok = false;
            List<Map<List<String>, List<String>>> filteredResult = new ArrayList<>(result);
            for (Map<List<String>, List<String>> state : filteredResult) {
                List<String> concatenated = Stream.concat(grammar.nonTerminals.stream(), grammar.terminals.stream()).collect(Collectors.toList());
                for (String element: concatenated) {
                    Map<List<String>, List<String>> goToElem = goTo(state, element);

                    if(!goToElem.isEmpty() && !included(result, goToElem)){
                        result.add(goToElem);
                        ok = true;
                    }
                }
            }
        }

        return result;
    }

    private boolean included(List<Map<List<String>, List<String>>> result, Map<List<String>, List<String>> goToElem) {
        return result.stream().anyMatch((listOfStates) -> listOfStates.entrySet().containsAll(goToElem.entrySet()));
    }
}

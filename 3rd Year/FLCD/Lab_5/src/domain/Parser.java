package domain;
import adt.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class Parser {
    private final Grammar grammar;

    public Parser(Grammar grammar) {
        this.grammar = grammar;
    }

    public Map<List<String>, List<String>> closureLR(String input) {
        //exemplu S -> .A B
        Map<List<String>, List<String>> P = new HashMap<>();
        List<String> lineListT = Arrays.asList(input.split("->"));
        List<String> lineList =
                lineListT.stream().map(String::trim).collect(Collectors.toList());

        //lineList : [S, .A B]
        List<String> key = Arrays.asList(lineList.get(0).strip().split(" \\| "));
        List<String> value = new ArrayList<>();
        String[] token = lineList.get(1).split("\\|");
        //split la lista dupa |
        for (var str : token) {
            value.add(str.strip());
        }
        P.put(key, value);
        int size = 0, index;
        String nonT;
        //pana aici o sa fie {[S]:[.A B]}
        while (size < P.size()) {
            size = P.size();
            Map<List<String>, List<String>> filteredP = new HashMap<>(P);
            for (Map.Entry element : filteredP.entrySet()) {
                value = (List<String>) element.getValue();
                // value o sa fie o lista de strings [.A B]
                for (String s : value) {
                    index = s.indexOf('.');
                    //gasim indexul punctului
                    if (index != -1 && index < s.length() - 1) {
                        //System.out.println(s);// daca exista punct, si nu e pe ultima pozitie
                        nonT = s.substring(index + 1).split(" ")[0]; // eliminam punctul
                        //System.out.println(nonT);
                        // o sa fie primul element acuma nonT
                        Map<List<String>, List<String>> filteredB = grammar.filterP(nonT);
                        //System.out.println(filteredB);
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
            List<String> value = new ArrayList<String>((List<String>) element.getValue());
            List<String> key = (List<String>) element.getKey();
            int index = -1;
            int i = 0;
            for(var x: value){
                index = x.indexOf("."+symbol);
                if(index != -1) {
                    break;
                }
                i++;
            }
            //                      a .adas B
            //                      012345678
            //            2-punct
            //            7-spatiu
            if (index != -1) {
                var new_symbol = "";
                int space = value.get(i).indexOf(" ", index);
                if(index == 0)
                    if(space != -1)
                        new_symbol = value.get(i).substring(1, space)
                                + " ."
                                + value.get(i).substring(space+1);
                    else
                        new_symbol = value.get(i).substring(1) + ".";
                else {
                    if(space != -1)
                        new_symbol = value.get(i).substring(0, index)
                                + value.get(i).substring(index + 1, space + 1) + "."
                                + value.get(i).substring(space + 1);
                    else
                        new_symbol = value.get(i).substring(0, index)
                                + value.get(i).substring(index + 1) + ".";
                }
                List<String> y = new LinkedList<>();
                y.add(new_symbol);
                value.set(i, new_symbol);
                Map<List<String>, List<String>> closure = closureLR(String.join(" | ", key) + " -> " + String.join(" | ", value));
                nestedMap.putAll(closure);
            }
        }
        return nestedMap;
    }

    public List<Map<List<String>, List<String>>> colCanLR() {
        List<Map<List<String>, List<String>>> result = new ArrayList<>();

        result.add(closureLR("S' -> .S"));
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

    public HashMap<Integer, Pair<String, HashMap<String, Integer>>> createLRTable(List<Map<List<String>, List<String>>> states) {
        HashMap<Integer, Pair<String, HashMap<String, Integer>>> lrTable = new HashMap<>();

        for (Map<List<String>, List<String>> state : states) {
            int position = states.indexOf(state);
            if(state.get(List.of("S'")) != null && state.get(List.of("S'")).contains("S.")){
                lrTable.put(position, new Pair("acc",new HashMap<String,Integer>()));
            }
            else if(hasReduce(state) != -1){
                lrTable.put(position,new Pair("reduce "+hasReduce(state),new HashMap<String,Integer>()));
            }
            else {
                lrTable.put(position,new Pair("shift",new HashMap<String,Integer>()));
                for (String element : Stream.concat(grammar.nonTerminals.stream(), grammar.terminals.stream())
                        .collect(Collectors.toList())) {

                    Map<List<String>, List<String>> goToRes = goTo(state, element);

                    if (!goToRes.isEmpty()) {
                        lrTable.get(position).second.put(element,states.indexOf(goToRes));
                    }
                }
            }
        }
        return lrTable;
    }

    private Integer hasReduce(Map<List<String>, List<String>> state) {
        for(Map.Entry element : state.entrySet()){
            List<String> value = new ArrayList<String>((List<String>) element.getValue());
            List<String> key = (List<String>) element.getKey();
            if(value.get(value.size() - 1).charAt(value.get(value.size() - 1).length() - 1) == '.'){
                String val = value.get(value.size() - 1);
                value.remove(value.size() - 1);
                value.add(val.substring(0, val.length() - 1));
                int index = 0;
                for(Map.Entry grammarElement : grammar.productionRules.entrySet()) {
                    List<String> gKey = (List<String>) grammarElement.getKey();
                    List<String> gValue = (List<String>) grammarElement.getValue();
                    if (gKey.equals(key) && gValue.equals(value)) {
                        break;
                    }
                    index ++;
                }
                if (index < grammar.productionRules.size())
                    return index;
                return -1;
            }
        }
        return -1;
    }

}

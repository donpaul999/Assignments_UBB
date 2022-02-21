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

    //afisare value dupa fiecare add
    public Map<List<String>, List<List<String>>> closureLR(String input) {
        Map<List<String>, List<List<String>>> P = new HashMap<>();
        List<String> lineListT = Arrays.asList(input.split("->"));
        List<String> lineList =
                lineListT.stream().map(String::trim).collect(Collectors.toList());
        //lineList : [S, .A B]
        List<String> key = Arrays.asList(lineList.get(0).strip().split(" \\| "));
        List<List<String>> value = new ArrayList<>();
        List<String> token = List.of(lineList.get(1).split("\\|"));
        for(var str:token){
            List<String> prod = Arrays.asList(str.strip().split(" "));
            value.add(prod);
        }
        //split la lista dupa |
        P.put(key, value);
        int size = 0;
        int index = -1;
        String nonT;
//        pana aici o sa fie {[S]:[[.A, B]}
        //pana aici e ok



        while (size <getSizeOfMap(P)) {
            size = getSizeOfMap(P);
            Map<List<String>, List<List<String>>> filteredP = clone(P);
            for (Map.Entry element : filteredP.entrySet()) {
                value = (List<List<String>>) element.getValue();
                // value o sa fie o lista de liste de strings [[b .A A]]
                for (List<String> s : value) {
                    index = -1;
                    for(String character: s){
                        if(character.charAt(0) == '.'){
                            index = s.indexOf(character);
                            break;
                        }
                    }
                    //we have the index of the dotted element
                    if (index != -1) {
                        s.set(index, s.get(index).substring(1));
                        nonT = s.get(index);
                        // nonT - dotted element
                        Map<List<String>, List<List<String>>> filteredB = clone(grammar.filterP(nonT));
                        for (Map.Entry elementB : filteredB.entrySet()) {
                            List<String> keyB = (List<String>) elementB.getKey();
                            List<List<String>> valueB = (List<List<String>>) elementB.getValue();
                            for(var q: valueB)
                                q.set(0, "."+q.get(0));
                            List<List<String>> oldValue = (List<List<String>>) element.getValue();
                            var X = clone(P);
                            //check if exist already
                            for (Map.Entry oldelement : X.entrySet()) {
                                List<String> oldkey = (List<String>) oldelement.getKey();
                                List<String> keyy = (List<String>) element.getKey();
                                if(keyB.get(0).equals(oldkey.get(0))){
                                    for(var qqq: (List<List<String>>)oldelement.getValue()){
                                        if(!valueB.contains(qqq)){
                                            valueB.add(qqq);
                                        }
                                    }
                                }
                            }
                            P.put(keyB, valueB);
                        }
                    }
                }
            }
        }
        return P;
    }

    public int getSizeOfMap(Map<List<String>, List<List<String>>> m){
        int c = 0;
        for (Map.Entry element : m.entrySet()) {
            List<List<String>> liststring = (List<List<String>>) element.getValue();
            for(var q: liststring)
                c += q.size();
        }
        return c;
    }

    public static Map<List<String>, List<List<String>>> clone(Map<List<String>, List<List<String>>> original)
    {
        Map<List<String>, List<List<String>>> copy = new HashMap<>();
        for (Map.Entry elementB : original.entrySet()) {
            List<String> key = new ArrayList<>();
            List<String> keyOriginal = (List<String>) elementB.getKey();
            key.add(keyOriginal.get(0));
            List<List<String>> values = new ArrayList<>();
            for(var vls: (List<List<String>>)elementB.getValue()){
                List<String> value = new ArrayList<>();
                for(var vl: vls){
                    value.add(vl);
                }
                values.add(value);
            }
            copy.put(key, values);
        }
        return copy;
    }

    public Map<List<String>, List<List<String>>> goTo(Map<List<String>, List<List<String>>> productions, String symbol) {
        Map<List<String>, List<List<String>>> nestedMap = new HashMap<>();
        Map<List<String>, List<List<String>>> productionscopy = clone(productions);
        for (Map.Entry element : productionscopy.entrySet()) {
            List<List<String>> values = (List<List<String>>) element.getValue();
            List<String> key = (List<String>) element.getKey();
            List<List<String>> newValues = new ArrayList<>();
            boolean symbolFound = false;
            for(var value: values){
                for(int i = 0; i < value.size(); i++) {
                    if (value.get(i).equals("."+symbol)) {
                        value.set(i, value.get(i).substring(1));
                        symbolFound = true;
                        if (i == value.size() - 1) {
                            value.set(i, value.get(i) + ".");
                        } else {
                            value.set(i + 1, "." + value.get(i + 1));
                        }
                        newValues.add(value);
                        break;
                    }
                }
            }
            if(symbolFound) {
                String closureString = key.get(0) + " ->";
                boolean or = false;
                for (var value : newValues) {
                    if (or)
                        closureString += " |";
                    or = false;
                    for (var val : value) {
                        closureString += " " + val;
                        or = true;
                    }
                }
                Map<List<String>, List<List<String>>> closure = clone(closureLR(closureString));
                nestedMap.putAll(closure);
            }
        }
        return nestedMap;
    }

    public List<Map<List<String>, List<List<String>>>> colCanLR() {
        List<Map<List<String>, List<List<String>>>> result = new ArrayList<>();
        result.add(clone(closureLR("S' -> .S")));
        System.out.println("S  ---> " + closureLR("S' -> .S"));
        boolean ok = true;
        while(ok) {
            ok = false;
            List<Map<List<String>, List<List<String>>>> filteredResult = new ArrayList<>(result);
            for (Map<List<String>, List<List<String>>> state : filteredResult) {
                List<String> concatenated = Stream.concat(grammar.nonTerminals.stream(), grammar.terminals.stream()).collect(Collectors.toList());
                for (String element: concatenated) {
                    Map<List<String>, List<List<String>>> goToElem = clone(goTo(state, element));
                    if(!goToElem.isEmpty() && !included(result, goToElem)){
                        System.out.println(state + "  goto("+element+")    ------>   " + goToElem);
                        result.add(goToElem);
                        ok = true;
                    }
                }
            }
        }
        return result;
    }
    private boolean included(List<Map<List<String>, List<List<String>>>> result, Map<List<String>, List<List<String>>> goToElem) {
        return result.stream().anyMatch((listOfStates) -> listOfStates.entrySet().containsAll(goToElem.entrySet()));
    }

    public HashMap<Integer, Pair<String, HashMap<String, Integer>>> createLRTable(List<Map<List<String>, List<List<String>>>> states) {
        HashMap<Integer, Pair<String, HashMap<String, Integer>>> lrTable = new HashMap<>();

        System.out.println("*********");
        System.out.println("Grammar: ");
        for (Map.Entry grammarElement : grammar.productionRules.entrySet()) {
            System.out.println(grammarElement);
        }
        System.out.println("*********");

        for (Map<List<String>, List<List<String>>> state : states) {
            int position = states.indexOf(state);
            if(state.get(List.of("S'")) != null && state.get(List.of("S'")).contains(List.of("S."))){
                lrTable.put(position, new Pair("acc",new HashMap<String,Integer>()));
            }
            else if(!hasReduce(state).equals(new Pair<>(-1, -1))){
                lrTable.put(position,new Pair("reduce "+hasReduce(state),new HashMap<String,Integer>()));
            }
            else {
                lrTable.put(position,new Pair("shift",new HashMap<String,Integer>()));
                for (String element : Stream.concat(grammar.nonTerminals.stream(), grammar.terminals.stream())
                        .collect(Collectors.toList())) {

                    Map<List<String>, List<List<String>>> goToRes = goTo(state, element);

                    if (!goToRes.isEmpty()) {
                        lrTable.get(position).second.put(element,states.indexOf(goToRes));
                    }
                }
            }
        }
        return lrTable;
    }

    private Pair<Integer, Integer> hasReduce(Map<List<String>, List<List<String>>> state) {
        Map<List<String>, List<List<String>>> states = clone(state);
        for(Map.Entry element : states.entrySet()){
            List<List<String>> value = (List<List<String>>) element.getValue();
            List<String> key = (List<String>) element.getKey();
            List<String> lastValue = value.get(0);
            if (lastValue.get(lastValue.size() - 1).charAt(lastValue.get(lastValue.size() - 1).length() - 1) == '.') {
                String val = lastValue.get(lastValue.size() - 1);
                lastValue.remove(lastValue.size() - 1);
                lastValue.add(val.substring(0, val.length() - 1));
                int index = 0;
                for (Map.Entry grammarElement : grammar.productionRules.entrySet()) {
                    List<String> gKey = (List<String>) grammarElement.getKey();
                    List<List<String>> gValue = (List<List<String>>) grammarElement.getValue();
                    for (List<String> gVal : gValue) {
                        if (gKey.equals(key) && gVal.equals(lastValue)) {
                            return new Pair<>(index, gValue.indexOf(gVal));
                        }
                    }
                    index++;
                }
            }
        }
        return new Pair<>(-1, -1);
    }

    private Pair<List<String>, List<List<String>>> searchProd(int reducer) {
        int index = 0;
        for ( Map.Entry e :  grammar.productionRules.entrySet() ) {
            if (index == reducer) {
                return new Pair<>((List<String>) e.getKey(), (List<List<String>>) e.getValue());
            }
            index++;
        }
        return null;
    }

    public List<String> parsingAlg(HashMap<Integer, Pair<String, HashMap<String, Integer>>>  lrTable, List<Map<List<String>, List<List<String>>>> C,  String word) {

        Map<List<String>, List<List<String>>> state = C.get(0);
        List<String> alpha = new ArrayList<>();
        List<Character> beta = new ArrayList<>();
        List<String> phi = new ArrayList<>();
        alpha.add("0");
        for (int i = 0; i <= word.length()-1; i++) {
            beta.add(word.charAt(i));
        }
        System.out.println(lrTable);
        while(true){
            int position = Integer.parseInt(alpha.get(alpha.size()-1));
            System.out.println(position);
            if (lrTable.get(position).first.equals("shift")) {
//                System.out.println("shift ---------");
//                System.out.println(beta);
//                System.out.println(alpha);
                Character a = beta.remove(0);
                //System.out.println(state);
                state = goTo(state, a.toString());
                alpha.add(a.toString());
               // System.out.println(state);
                alpha.add(Integer.toString(C.indexOf(state)));
//                System.out.println(beta);
//                System.out.println(alpha);
            } else if (lrTable.get(position).first.contains("reduce")) {
                //System.out.println("reduce ---------");
                String findReducer = lrTable.get(position).first.substring(7);
                int reducer = Integer.parseInt(findReducer.split(" ")[0]);
                Pair<List<String>, List<List<String>>> production = searchProd(reducer);
//                System.out.println("----------");
//                System.out.println(production);
//                System.out.println(production.second.size());
//                System.out.println(alpha);
//                System.out.println("-----------");
                for(int i = 0; i < 2 * production.second.size() && i <= alpha.size(); i++) {
                    alpha.remove(alpha.size() - 1);
                }
                state = goTo(C.get(Integer.parseInt(alpha.get(alpha.size()-1))), production.first.get(0));
                alpha.add(Character.toString(production.first.get(0).charAt(0)));
                alpha.add(Integer.toString(C.indexOf(state)));
                phi.add(findReducer.split(" ")[0]);
               // System.out.println(alpha);
            }
            else{
                //System.out.println("acc -------");
                //System.out.println(position);
               // System.out.println(phi);
                if(lrTable.get(position).first.equals("acc")){
                    Collections.reverse(phi);
                    return phi;
                }
                else{
                    return null;
                }

            }
        }
    }

    public void createTable(List<String> productions){
        List<Pair<String,Pair<Integer,Integer>>> table = new ArrayList<>();
        table.add(new Pair<>("S", new Pair<>(-1,-1)));
        int k=0;
        for(String production : productions){
            while(!grammar.nonTerminals.contains(table.get(k).first)){
                k++;
            }
            Pair<List<String>, List<List<String>>> prod = searchProd(Integer.parseInt(production));
            int index=0;
            table.add(new Pair(String.valueOf(prod.second.get(0)),new Pair(k, -1)));
            index++;
            for(; index < prod.second.size(); index++){
                table.add(new Pair(String.valueOf(prod.second.get(0)),new Pair(k, k + index)));
            }
            k++;
        }
        System.out.println(table);
    }
}

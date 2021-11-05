package domain;

import adt.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FiniteAutomata {
    private List<String> Q;
    private List<String> E;
    private String q0;
    private List<String> F;
    private HashMap<Pair<String, String>, List<String>> T = new HashMap<>();


    public FiniteAutomata(String fileName) throws Exception {
        readFA(fileName);
    }

    private void readFA(String fileName) throws Exception {
        List <String> stringList;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            Q = getStatesFromLine(br);
            E = getStatesFromLine(br);
            q0 = getStatesFromLine(br).get(0);
            F = getStatesFromLine(br);

            br.readLine();
            while ((line = br.readLine()) != null) {
                String source = line.split("->")[0].replace("(","").replace(")","").split(",")[0].trim();
                String transition = line.split("->")[0].replace("(","").replace(")","").split(",")[1].trim();
                String destination = line.split("->")[1].trim();
                Pair <String, String> pair = new Pair<>(source, transition);
                try {
                    if (!T.get(pair).isEmpty()) {
                        stringList = new ArrayList<>(T.get(pair));
                        stringList.add(destination);
                        T.put(pair, stringList);
                    }
                } catch (NullPointerException e) {
                    T.put(pair, Collections.singletonList(destination));
                }
            }

            validateFA();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validateFA() throws Exception {
        if (!Q.contains(q0)) {
            throw new Exception("q0 not in Q!");
        }
        for (String s : F) {
            if (!Q.contains(s)) {
                throw new Exception("final state not in Q!");
            }
        }

        for (Pair<String, String> p : T.keySet()) {
            if (!Q.contains(p.first)) {
                throw new Exception("source state not in Q!");
            }
            if (!E.contains(p.second)) {
                throw new Exception("transition not in E!");
            }

            List <String> destinations = T.get(p);

            for (String d : destinations) {
                if (!Q.contains(d)) {
                    throw new Exception("Destination state not in Q!");
                }
            }
        }
    }

    private List<String> getStatesFromLine(BufferedReader br) throws IOException {
        String line = br.readLine();
        List <String> list =  Arrays.asList(line.split(" "));
        return list.subList(2, list.size());
    }

    public List<String> getStates() {
        return Q;
    }

    public List<String> getAlphabet() {
        return E;
    }

    public HashMap<Pair<String, String>, List<String>> getTransitions() {
        return T;
    }

    public List<String> getFinalStates() {
        return F;
    }

    public boolean isDFA() {
        for (Pair<String, String> p : T.keySet()) {
            if (T.get(p).size() > 1) {
                return false;
            }
        }
        return true;
    }

    public boolean isAcceptedByFA(String sequence) {
        if (!isDFA())
            return false;
        String currentState = q0, s = "";
        Pair p;
        for (int i = 0; i < sequence.length(); i += 1) {
            s = String.valueOf(sequence.charAt(i));
            p = new Pair(currentState, s);
            try {
                if (!T.get(p).isEmpty()) {
                    currentState = T.get(p).get(0);
                }
            } catch (Exception e) {
                return false;
            }
        }
        if(!F.contains(currentState))
            return false;
        return true;
    }
}

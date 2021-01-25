import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main_stream {
    public static void main(String[] args){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8,9,10,11,12,14,15);
        String ans = numbers
                    .stream()
                    .filter(s -> s % 3 == 0 && s % 2 == 0)
                    .map(s -> "At" + s.toString() + "km")
                    .collect(Collectors.joining());
        System.out.println(ans);
    }
}

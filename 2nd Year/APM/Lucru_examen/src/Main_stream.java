import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
? - read null, write Object
extend - read, get, also write null
super - write, add
? extend A
? super C

 */
public class Main_stream {
    public static void main(String[] args){
        //collect(Collectors.toList())
        //collect(Collectors.joining(""))

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8,9,10,11,12,14,15);
        Integer ans = numbers
                    .stream()
                    .filter(s -> s % 3 == 0 || s % 7 == 0)
                    .map(s -> s - 1)
                    .reduce(0, (total, s) -> (total + s) % 5);
        System.out.println(ans);
    }
}

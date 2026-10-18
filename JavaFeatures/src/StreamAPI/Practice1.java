package StreamAPI;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Practice1 {
    public static void main(String[] args){
        List<Integer> transactionAmounts =
                List.of(5000, 15000, 25000, 8000, 12000, 30000);

        boolean res=transactionAmounts.stream().noneMatch(n->n<0);

        System.out.println(res);







    }
}

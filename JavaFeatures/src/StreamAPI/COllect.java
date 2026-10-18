package StreamAPI;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

record Transaction1(String id, double amount, String status) {}
public class COllect {
    public static void main(String[] args){
        List<Transaction1> transactions = List.of(
                new Transaction1("TXN101", 5000, "SUCCESS"),
                new Transaction1("TXN102", 15000, "FAILED"),
                new Transaction1("TXN103", 25000, "SUCCESS"),
                new Transaction1("TXN104", 8000, "SUCCESS"),
                new Transaction1("TXN105", 12000, "FAILED"),
                new Transaction1("TXN106", 30000, "SUCCESS"),
                new Transaction1("TXN107", 18000, "SUCCESS")
        );

        Long res=transactions.stream()
                .filter(transaction1 ->transaction1.status().equals("SUCCESS"))
                .count();
        System.out.println(res);

        List<String> res1=transactions.stream()
                .filter(transaction1 -> transaction1.status().equalsIgnoreCase("SUCCESS"))
                .map(transaction1 -> transaction1.id())
                .collect(Collectors.toList());

        System.out.println(res1);





    }
}

package StreamAPI;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GroupByAndPartitionBy {
    record Transaction1(String id, double amount, String status) {}

    public static void main(String[] args) {

        List<Transaction1> transactions = List.of(
                new Transaction1("TXN101", 5000, "SUCCESS"),
                new Transaction1("TXN102", 15000, "FAILED"),
                new Transaction1("TXN103", 25000, "SUCCESS"),
                new Transaction1("TXN104", 8000, "SUCCESS"),
                new Transaction1("TXN105", 12000, "FAILED"),
                new Transaction1("TXN106", 30000, "SUCCESS"),
                new Transaction1("TXN107", 18000, "SUCCESS")
        );


        Map<String,List<Transaction1> > res3 =transactions.stream()
                .collect(Collectors.groupingBy(Transaction1::status));

        System.out.println(res3);

        res3.forEach((status,total)
                ->
                        System.out.println(status+"->"+
                            total.size()));



        Map<Boolean, List<Transaction1>> partitioned =
                transactions.stream()
                        .collect(Collectors.partitioningBy(
                                tx -> tx.status().equals("SUCCESS")
                        ));

        partitioned.forEach((isSuccess, txns) ->
                System.out.println(
                        (isSuccess ? "SUCCESS" : "FAILED") + " -> " +
                                txns.stream()
                                        .map(Transaction1::id)
                                        .toList()
                ));

        Map<String, Double> totalAmountByStatus =
                transactions.stream()
                        .collect(Collectors.groupingBy(
                                Transaction1::status,
                                Collectors.summingDouble(Transaction1::amount)
                        ));

        System.out.println("Total amount by status: " + totalAmountByStatus);


        Map<String, Optional<Transaction1>> res4=transactions.stream()
                .collect(Collectors.groupingBy(Transaction1::status
                ,Collectors.maxBy(
                        Comparator.comparing(Transaction1::amount)
                        )));


        System.out.println(res4);


    }
}

package StreamAPI;

import java.util.List;

record Transaction(String id, double amount) {}

record Account(String accountId, List<Transaction> transactions) {}

public class Main {
    public static void main(String[] args) {

        List<Account> accounts = List.of(
                new Account("ACC101", List.of(
                        new Transaction("TXN001", 5000),
                        new Transaction("TXN002", 15000),
                        new Transaction("TXN003", 25000)
                )),
                new Account("ACC102", List.of(
                        new Transaction("TXN004", 8000),
                        new Transaction("TXN005", 12000)
                )),
                new Account("ACC103", List.of(
                        new Transaction("TXN006", 30000),
                        new Transaction("TXN007", 2000)
                ))
        );

        List<String> res=accounts.stream()
                .flatMap(listlu->listlu.transactions().stream())
                .filter(n->n.amount()>=10000)
                .map(n-> n.id())
                .toList();

        res.forEach(System.out::println);





    }
}
package CompletableFuture;
import java.util.concurrent.CompletableFuture;

import java.util.concurrent.TimeUnit;

public class AsyncChallenges {

    public static void main(String[] args) {
        System.out.println("--- Running Challenge 1: Travel Aggregator ---");
        runChallenge1();
        sleep(3500);
        System.out.println("\n--- Running Challenge 2: E-Commerce Pipeline ---");
        runChallenge2();
        sleep(2000);
    }
    public static void runChallenge1() {
        long startTime = System.currentTimeMillis();

        CompletableFuture<Double> flightFuture = CompletableFuture.supplyAsync(() -> {
            sleep(2000);
            return 450.00;
        });

        CompletableFuture<Double> hotelFuture = CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            return 250.00;
        });


        flightFuture
                .thenCombine(hotelFuture,
                        Double::sum
                        )
                .thenAccept(System.out::println)
                .thenRun(() -> {
                    long duration = System.currentTimeMillis() - startTime;
                    System.out.println("Challenge 1 completed in: " + duration + " ms");
                });
    }

    static class UserProfile {
        String name;
        String cardToken;
        UserProfile(String name, String cardToken) { this.name = name; this.cardToken = cardToken; }
    }

    static class PaymentStatus {
        String transactionId;
        boolean isSuccess;
        PaymentStatus(String transactionId, boolean isSuccess) { this.transactionId = transactionId; this.isSuccess = isSuccess; }
    }


    public static CompletableFuture<UserProfile> fetchUserProfile(String userId) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(800);
            return new UserProfile("Alice", "TOKEN_XYZ_123");
        });
    }

    public static CompletableFuture<PaymentStatus> processPayment(UserProfile profile) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(500);
            System.out.println("Processing payment with token: " + profile.cardToken);
            return new PaymentStatus("TXN-998877", true);
        });
    }

    public static void runChallenge2() {
        String targetUserId = "user_007";

        fetchUserProfile(targetUserId).thenCompose((name)->processPayment(name))
                .thenAccept(status->{
                    System.out.println(status.transactionId + "status"+status.isSuccess);
                });
    }

    // Helper method to simulate network delay without messy try-catch blocks
    private static void sleep(long milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

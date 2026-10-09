package CompletableFuture;

import java.util.concurrent.CompletableFuture;

import static CompletableFuture.AllMethods.sleep;

public class Exception {
    static CompletableFuture<String> paymentService() {
        return CompletableFuture.supplyAsync(() -> {
                    sleep(1000);
                    System.out.println("Payment service");
                    throw new RuntimeException("payment gateway failed");
                }
        );
    }
    public static void main(String[] args) {
        CompletableFuture<String> result = paymentService()
                .exceptionally(ex -> {
                    System.out.println("here" + ex.getMessage());

                    return "Payment failed";
                });
        System.out.println(result.join());
    }
}

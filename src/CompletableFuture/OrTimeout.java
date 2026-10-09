package CompletableFuture;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class OrTimeout {

    static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {

        CompletableFuture<String> payment =
                CompletableFuture.supplyAsync(() -> {

                    sleep(5000);

                    return "Payment successful";

                }).orTimeout(3, TimeUnit.SECONDS)
                                .exceptionally(ex->{
                                    return "timeout";
                                });

        System.out.println(payment.join());
    }
}

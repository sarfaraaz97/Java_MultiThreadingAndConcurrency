package CompletableFuture;
import java.util.concurrent.CompletableFuture;

public class Cancellation {

    public static void main(String[] args)
            throws InterruptedException {

        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(() -> {

                    System.out.println("Task started");

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    System.out.println("Task finished");

                    return "Product available";
                });

        Thread.sleep(500);

        boolean result = future.cancel(true);

        System.out.println("Cancelled: " + result);
        System.out.println("Is cancelled: " + future.isCancelled());
        System.out.println("Is done: " + future.isDone());

        Thread.sleep(6000);
    }
}
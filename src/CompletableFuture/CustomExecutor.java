package CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomExecutor {

    public static void main(String[] args) {

        ExecutorService executor =
                Executors.newFixedThreadPool(3);

        CompletableFuture<String> customer =
                CompletableFuture.supplyAsync(() -> {

                    System.out.println(
                            "Customer thread: "
                                    + Thread.currentThread().getName()
                    );

                    return "Sarfaraaz";

                }, executor);


        CompletableFuture<String> result =
                customer.thenApplyAsync(name -> {

                    System.out.println(
                            "Processing thread: "
                                    + Thread.currentThread().getName()
                    );

                    return name.toUpperCase();

                }, executor);


        System.out.println(
                "Final result: " + result.join()
        );


        executor.shutdown();
    }
}

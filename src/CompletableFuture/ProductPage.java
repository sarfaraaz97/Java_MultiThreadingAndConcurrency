package CompletableFuture;

import java.util.concurrent.*;

public class ProductPage {

    private static final ExecutorService executor =
            Executors.newFixedThreadPool(3);

    static CompletableFuture<String> fetchProduct() {
        return CompletableFuture.supplyAsync(() -> {
            sleep(500);
            return "Laptop";
        }, executor);
    }

    static CompletableFuture<Integer> fetchInventory() {
        return CompletableFuture.supplyAsync(()->{
            sleep(1200);
            return 15;
        },executor).orTimeout(1, TimeUnit.SECONDS)
                .exceptionally(e->{
                    System.out.println("Inventory not available"+e.getMessage());
                    return -1;
                });
    }

    static CompletableFuture<Double> fetchRating() {
        return CompletableFuture.<Double>supplyAsync(()->{
            sleep(300);
            throw new RuntimeException("service failed");
        },executor).completeOnTimeout(
                0.0,
                1,
                TimeUnit.SECONDS
        ).handle(
                ((rating,ex)->{
                    if(ex!=null) {
                        return 0.0;
                    }
                    return rating;
                })
        );

    }

    static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try {
            CompletableFuture<String> future1=fetchProduct();
            CompletableFuture<Integer> future2=fetchInventory();
            CompletableFuture<Double>future3=fetchRating();

            CompletableFuture<String> result1 = future1.thenCombineAsync(
                    future2,
                    (productname,inventory)-> productname+"|"+inventory,executor
            );

            CompletableFuture<String> finalResult=result1.thenCombineAsync(
                    future3,
                    (res,rating)->{
                        return res+"|"+rating;
                    },executor);

            System.out.println(finalResult.join());


        } finally {
            executor.shutdown();
        }
    }
}
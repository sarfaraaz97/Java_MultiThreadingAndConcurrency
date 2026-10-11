package CompletableFuture;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public class AsyncMethods {
    static void main() {
        CompletableFuture<String> future=CompletableFuture.completedFuture("Order101");

        System.out.println(Thread.currentThread().getName());

        CompletableFuture<String> res=future.thenApplyAsync((order)->{
            System.out.println("Processing"+Thread.currentThread().getName());
            return order.toUpperCase();
        });


        System.out.println(res.join());

        CompletableFuture<String> result=fetchOrder().thenComposeAsync((customerId)->fetchCustomer(customerId));
        System.out.println(result.join());

    }
    static CompletableFuture<String>fetchOrder(){
        return CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName());
            return "Customer01";
        });
    }
    static CompletableFuture<String>fetchCustomer(String customerId){
        return CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName());
            return "customer details"+ customerId;
        });
    }

}

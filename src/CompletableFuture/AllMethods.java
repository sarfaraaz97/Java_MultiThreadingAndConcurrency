package CompletableFuture;

import java.util.concurrent.CompletableFuture;


public class AllMethods {

    static CompletableFuture<String> fetchCustomer() {
        return CompletableFuture.supplyAsync(() -> {
            sleep(500);

            System.out.println("Customer service called");

            return "Sarfaraaz";
        });
    }
    static CompletableFuture<String> getLoyaltyDetails(String customerName) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(700);

            System.out.println("Loyalty service called for " + customerName);

            return "Gold Member";
        });
    }

    static CompletableFuture<String> checkInventory() {
        return CompletableFuture.supplyAsync(() -> {
            sleep(800);

            System.out.println("Inventory service called");

            return "Product available";
        });
    }

    static CompletableFuture<Integer> calculatePrice() {
        return CompletableFuture.supplyAsync(() -> {
            sleep(600);

            System.out.println("Pricing service called");

            return 1000;
        });
    }


    static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args){
        CompletableFuture<String> inventory=checkInventory();
        CompletableFuture<Integer> price=calculatePrice();
        CompletableFuture<String> customer=fetchCustomer();

        CompletableFuture<Void> all=CompletableFuture.allOf(
                inventory,
                price,
                customer
        );


        //thenApply
        CompletableFuture<String> CustomerCaps=inventory.thenApply(String::toUpperCase);
        String res=CustomerCaps.join();
        System.out.println(res);

        CompletableFuture<String> compose=customer.thenCompose((name)->getLoyaltyDetails(name));

        String res1=compose.join();
        System.out.println(res1);

        CompletableFuture<String> combine=customer.thenCombine(price,(cname,totalP)->cname+"order placed"+totalP);

        String combineres=combine.join();
        System.out.println(combineres);




        //thenCompose


        String customername=customer.join();
        System.out.println(customername);




    }
}

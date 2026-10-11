package CompletableFuture;

import java.lang.Exception;
import java.util.concurrent.CompletableFuture;

public class Complete {
    public static void main(String[] args){
        CompletableFuture<String> future=new CompletableFuture<>();
        System.out.println(future.isDone());
        boolean completed=future.complete("payment done buddy");
        System.out.println(completed);
        System.out.println(future.join());

        CompletableFuture<Integer>future1=new CompletableFuture<>();
        future1.completeExceptionally(new RuntimeException("payment service is unavailable"));

        try{
            System.out.println(future1.join());
        }catch(Exception e){
            System.out.println(e.getCause().getMessage());
        }




    }
}

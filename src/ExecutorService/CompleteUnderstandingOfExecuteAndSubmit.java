package ExecutorService;

import java.util.concurrent.*;

public class CompleteUnderstandingOfExecuteAndSubmit {

    public static void main(String[] args) throws InterruptedException{
        ExecutorService executor= Executors.newFixedThreadPool(5);

        //execute
        executor.execute(()->{
            System.out.println("Sending an email"+Thread.currentThread().getName());
        });

        Future<Integer> future=executor.submit(()->{
            System.out.println("Calculating"+Thread.currentThread().getName());
            Thread.sleep(1000);
            return 10+25;
        });

        try{
            Integer result=future.get(2, TimeUnit.SECONDS);
            System.out.println(
                    "Calculationresult"
                            +result
            );
        } catch (TimeoutException e) {
            System.out.println("Task timed out");
            future.cancel(true);
        } catch (ExecutionException e) {
            System.out.println(
                    "Task failed: "
                            +e.getCause()
            );
        }finally {
            executor.shutdown();
        }
    }
}

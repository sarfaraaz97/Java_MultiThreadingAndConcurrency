package ExecutorService;

import java.util.concurrent.*;

public class SubmitCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor= Executors.newFixedThreadPool(3);

        Callable<Integer>task=()->{
            Thread.sleep(1000);
            return 100+100;
        };

        Future<Integer> future=executor.submit(task);
        Integer result=future.get();
        System.out.println(result);
        executor.shutdown();

    }
}

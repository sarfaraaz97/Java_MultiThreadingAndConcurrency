package ExecutorService;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CallerRunsPolicy implements ThreadFactory {
    private final AtomicInteger counter = new AtomicInteger(1);
    @Override
    public Thread newThread(Runnable task) {
        Thread thread = new Thread(task);
        thread.setName(
                "Order worker-" + counter.getAndIncrement()
        );
        return thread;
    }
    public static void main(String[] args) {

        ThreadFactory factory = new CallerRunsPolicy();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                1,                              // corePoolSize
                1,                              // maximumPoolSize
                0,                             // keepAliveTime
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),    // queue
                factory,
                new ThreadPoolExecutor.CallerRunsPolicy()// custom ThreadFactory
        );
        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            pool.execute(() -> {
                System.out.println(
                        "Task " + taskId +
                                " running on " +
                                Thread.currentThread().getName()
                );

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        pool.shutdown();
    }
}
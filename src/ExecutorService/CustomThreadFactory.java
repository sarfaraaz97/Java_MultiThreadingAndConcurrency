package ExecutorService;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadFactory implements ThreadFactory {
    private final AtomicInteger counter = new AtomicInteger(1);
    @Override
    public Thread newThread(Runnable task) {
        Thread thread = new Thread(task);
        thread.setName(
                "Payment-worker-" + counter.getAndIncrement()
        );
        return thread;
    }
    public static void main(String[] args) {

        ThreadFactory factory = new CustomThreadFactory();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                2,                              // corePoolSize
                4,                              // maximumPoolSize
                10,                             // keepAliveTime
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),    // queue
                factory                         // custom ThreadFactory
        );
        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            pool.execute(() -> {
                System.out.println(
                        "Task " + taskId +
                                " running on " +
                                Thread.currentThread().getName()
                );
            });
        }
        pool.shutdown();
    }
}
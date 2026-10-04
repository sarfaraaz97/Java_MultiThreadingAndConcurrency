package ExecutorService;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorBasic {
    public static void main(String[] args) {
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(
                        2,
                        4,
                        10,
                        TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(2)
                );
        for (int i = 1; i <= 7; i++) {
            int taskId = i;
            executor.execute(() -> {
                System.out.println(
                        "Task " + taskId +
                                " started by " +
                                Thread.currentThread().getName()
                );

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println(
                        "Task " + taskId +
                                " completed by " +
                                Thread.currentThread().getName()
                );
            });
        }
        executor.shutdown();
    }
}

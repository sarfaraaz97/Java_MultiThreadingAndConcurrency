package ExecutorService;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDesign {
    public static void main(String[] args){
        ThreadPoolExecutor threadPoolExecutorforCPUBound=new ThreadPoolExecutor(
                2,
                2,
                0,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        ThreadPoolExecutor threadPoolExecutorforIoBound=new ThreadPoolExecutor(
                3,
                3,
                0,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        for(int i=1;i<=5;i++){
            int orderId=i;
            threadPoolExecutorforCPUBound.execute(()->{
                System.out.println(
                        "Calculating the order"+
                                orderId+
                                    Thread.currentThread().getName()
                );

                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println("Calculation completed"+orderId);
            });
        }
        for(int i=1;i<=6;i++){

            int orderId=i;

            threadPoolExecutorforIoBound.execute(()->{
                System.out.println(
                        "paymemnt api processing"+
                                orderId+
                                    Thread.currentThread().getName()
                );

                try{
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println(
                        "Task completed"+
                                orderId
                );
            });
        }
        try{
            if(!threadPoolExecutorforCPUBound.awaitTermination(5,TimeUnit.SECONDS)){
                threadPoolExecutorforCPUBound.shutdownNow();
            }
            if(!threadPoolExecutorforIoBound.awaitTermination(5,TimeUnit.SECONDS)){
                threadPoolExecutorforIoBound.shutdownNow();
            }
        }catch (InterruptedException e){
            threadPoolExecutorforCPUBound.shutdownNow();
            threadPoolExecutorforIoBound.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}

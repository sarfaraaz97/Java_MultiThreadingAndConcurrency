package ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ShutDown {
    public static void main(String[] args) {
        ExecutorService executor= Executors.newFixedThreadPool(3);

        for(int i=0;i<=5;i++){
            int taskId=i;
            executor.execute(()->{
                System.out.println("task"
                        +taskId
                        +"started by"
                        +Thread.currentThread().getName()
                );
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                     Thread.currentThread().interrupt();
                    System.out.println("Task"
                            +taskId
                            +"has interrupted"
                    );
                    return;
                }

                System.out.println("task"+taskId+"has completed");
            });

            executor.shutdown();

            try{
                if(!executor.awaitTermination(
                        10,
                        TimeUnit.SECONDS)){
                    executor.shutdownNow();
                }

            }catch (InterruptedException e){
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }


        }
    }
}

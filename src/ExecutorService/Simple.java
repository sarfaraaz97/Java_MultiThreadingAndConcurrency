package ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Simple {

    public static void main(String[] args){

        ExecutorService execute= Executors.newFixedThreadPool(3);

        for(int i=0;i<10;i++){

            int taskId=i;
            execute.execute(()->{
                System.out.println("Task"+taskId+"running on"+Thread.currentThread().getName());

                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }
                System.out.println(taskId+"Task completed by "+Thread.currentThread().getName());
            });

        }
        execute.shutdown();

    }

}

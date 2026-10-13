package ConcurrencyProblems.ProducerAndConsumer;

import java.util.concurrent.*;
import java.util.function.Consumer;

public class Version1 {
    private static final ArrayBlockingQueue<Integer> blockingQueue=new ArrayBlockingQueue<>(5);

    public static void main(String[] args){
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(
                5,
                5,
                0,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3)
        );

        CompletableFuture<Void> producer1=CompletableFuture.runAsync(()->
                Producer(1),
                threadPoolExecutor
        );
        CompletableFuture<Void>producer3=CompletableFuture.runAsync(()->
                Producer(2),
                threadPoolExecutor);

        CompletableFuture<Void> result=CompletableFuture.allOf(
                producer1,
                producer3
        );

        threadPoolExecutor.submit(()-> Consumer(1));
        threadPoolExecutor.submit(()-> Consumer(2));
        threadPoolExecutor.submit(()-> Consumer(3));

        CompletableFuture<Void> shutdownpls=result.thenRun(
                ()->{
                    for(int i=0;i<3;i++){
                        try{
                            blockingQueue.put(-1);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            throw new RuntimeException(e);
                        }
                    }
                }
        );

        try{
            shutdownpls.join();
        }finally {
            threadPoolExecutor.shutdown();
        }




    }
    private static void Producer(int producerId){
        for(int i=1;i<=10;i++){
            int jobId=producerId*100+i;

            try{
                blockingQueue.put(jobId);
                System.out.println("Producer"+producerId+"has added"+jobId);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
    private static void Consumer(int consumeId){
        while(!Thread.currentThread().isInterrupted()){
            try{
                int jobId=blockingQueue.take();
                if(jobId==-1){
                    System.out.println("Consumer Id"+consumeId+"stopping");
                    return;
                }
                System.out.println("Consumer " + consumeId + " processing job " + jobId);
                Thread.sleep(300);
            }catch (InterruptedException ex){
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

}

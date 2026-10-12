import java.util.concurrent.*;

public class ProducerConsumer_handson {
    private static final ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(5);

    public static void main(String[] args) {
        ExecutorService executorService=Executors.newFixedThreadPool(5);

        CompletableFuture<Void> producer1=CompletableFuture.runAsync(
                    ()->produce(1),
                    executorService
        );
        CompletableFuture<Void>producer2=CompletableFuture.runAsync(
                    ()->produce(3),
                    executorService
        );
        CompletableFuture<Void> all=CompletableFuture.allOf(
                producer1,
                producer2
        );

        executorService.submit(()->consume(1));
        executorService.submit(()->consume(2));
        executorService.submit(()->consume(3));

        CompletableFuture<Void> shutdownFuture=all.thenRun(
                ()->{
                    for(int i=0;i<3;i++){
                        try {
                            blockingQueue.put(-1);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            throw new RuntimeException(e);
                        }
                    }
                }
        );


        try {
            shutdownFuture.join();
        } finally {
            executorService.shutdown();
        }


    }
    private static void produce(int produceId) {
        for(int i=1;i<=10;i++) {
            int jobId=produceId*100+i;

            try {
                blockingQueue.put(jobId);
                System.out.println(
                        "Producer " + produceId + " produced job " + jobId
                );
            }catch(InterruptedException exception){
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
    private static void consume(int consumerId){

        while(!Thread.currentThread().isInterrupted()){
            try{
                int jobId=blockingQueue.take();

                if(jobId==-1){
                    System.out.println(
                            "Consumer " + consumerId + " stopping"
                    );
                    return;
                }
                System.out.println(
                        "Consumer " + consumerId + " processing job " + jobId
                );
                Thread.sleep(400);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

    }
}









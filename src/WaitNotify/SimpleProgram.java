package WaitNotify;
import java.util.LinkedList;
import java.util.Queue;

public class SimpleProgram {

    static class Buffer{

        private final Queue<Integer> queue=new LinkedList<>();
        private final int capacity=3;

        public synchronized void produce(int val) throws InterruptedException{

            while(queue.size()==capacity){
                System.out.println("Producer added");
                wait();
            }
            queue.add(val);

            System.out.println("Produced added"+val);

            notifyAll();

        }


        public synchronized int consumer() throws InterruptedException{

             while(queue.isEmpty()){
                 System.out.println(" ");
                 wait();
             }
             int val= queue.remove();

             System.out.println(queue.size());

             notifyAll();

             return val;
        }

        public static void main(String[] args){

            Buffer b=new Buffer();

            Thread producer = new Thread(() -> {
                for(int i=1;i<=10;i++){
                    try{
                        b.produce(i);
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            },"Producer Thread");


            Thread consumer=new Thread(() ->{
                for(int i=1;i<=10;i++){
                    try{
                        b.consumer();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            },"Consumer Thread");


            producer.start();
            consumer.start();

        }
    }
}

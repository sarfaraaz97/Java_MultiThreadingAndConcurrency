package ConcurrencyProblems.ProducerAndConsumer;

import java.util.LinkedList;
import java.util.Queue;

class Version2 {
    private final Queue<Integer> queue=new LinkedList<>();
    private final int capacity;

    public Version2(int capacity){
        this.capacity=capacity;
    }

    public void produce(int value) throws InterruptedException{
        synchronized (queue){
            while(queue.size()==capacity){
                System.out.println("queue is full");
                queue.wait();
            }
            queue.add(value);
            System.out.println("producer added this "+value);
            queue.notifyAll();
        }
    }

    public int consume() throws InterruptedException{
        synchronized (queue){
            while(queue.isEmpty()){
                System.out.println("queue is empty");
                queue.wait();
            }
            int item=queue.poll();
            System.out.println("consumer has removed this" + item);
            queue.notifyAll();
            return item;
        }
    }
}

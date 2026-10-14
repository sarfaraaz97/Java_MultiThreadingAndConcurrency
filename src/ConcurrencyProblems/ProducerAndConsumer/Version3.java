package ConcurrencyProblems.ProducerAndConsumer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Version3 {
    private final Deque<Integer> queue=new ArrayDeque<>();
    private final int capacity;

    public Version3(int capacity){
        this.capacity=capacity;
    }

    private final ReentrantLock lock=new ReentrantLock();
    private final Condition notFull=lock.newCondition();
    private final Condition notEmpty=lock.newCondition();

    public void produce(int value) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (queue.size() >= capacity) {
                System.out.println("queue is full producer is waiting");
                notFull.await();
            }
            queue.addLast(value);
            System.out.println(Thread.currentThread().getName()+"Producer has addded:" + value);
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }
    public int consume() throws InterruptedException{
        lock.lockInterruptibly();
        try{
            while(queue.isEmpty()){
                System.out.println("queue is empty consumer is waiting");
                notEmpty.await();
            }
            int val=queue.removeFirst();
            System.out.println("item has removed from queue"+val);
            notFull.signal();
            return val;
        }finally {
            lock.unlock();
        }
    }

}

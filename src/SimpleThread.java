import org.w3c.dom.ls.LSOutput;

public class SimpleThread {
    public static void main(String[] args){

        System.out.println(Thread.currentThread().getName());

        Thread t=new Thread(() ->{
            System.out.println("worker : "+Thread.currentThread().getName());
        },"worker1");

        t.start();

        System.out.println("main thread continues");
    }
}

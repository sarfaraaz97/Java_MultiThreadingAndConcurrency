package DeadLock;

public class Dead {
    private final Object lock1=new Object();
    private final Object lock2=new Object();

    public void func1(){
        synchronized (lock1){
            System.out.println("t1 acquired by lock1");
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("thread 1 waiting for Lock 2");

        synchronized (lock2){
            System.out.println("t1 acquired by lock2");
        }

    }
    public void func2(){
        synchronized (lock1){
            System.out.println("t2 acquired by lock1");
        }
        try{
            Thread.sleep(100);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }

        System.out.println("thread 2 waiting for lock1");
        synchronized (lock2){
            System.out.println("t2 acquired by lock2");
        }
    }
    public static void main(String[] args){
        Dead obj=new Dead();

        Thread t1=new Thread(obj::func1,"Thread1");
        Thread t2=new Thread(obj::func2,"Thread2");

        t1.start();
        t2.start();



    }
}

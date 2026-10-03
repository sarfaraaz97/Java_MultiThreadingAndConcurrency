package RaceConditionCreation;

public class Problem {
    private int count=0;
    public synchronized void increement(){
        count++;
    }
    public int getCount(){
        return count;
    }
    public static void main(String[] args) throws InterruptedException{
        Problem p=new Problem();
        Thread t1=new Thread(() -> {
            for(int i=0;i<1000;i++){
                p.increement();
            }
        });

        Thread t2=new Thread(() ->{
            for(int i=0;i<1000;i++){
                p.increement();
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(p.getCount());

    }
}

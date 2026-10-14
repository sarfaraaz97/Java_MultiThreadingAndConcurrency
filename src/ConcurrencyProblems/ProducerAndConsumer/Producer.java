package ConcurrencyProblems.ProducerAndConsumer;

class Producer implements Runnable {
    private final Version2 sharedbuffer;
    private final int startValue;

    public Producer(Version2 sharedbuffer,int startvalue){
        this.sharedbuffer=sharedbuffer;
        this.startValue=startvalue;
    }

    public void run(){
        int value=startValue;
        for(int i=0;i<10;i++){
            try{
                sharedbuffer.produce(value++);
                Thread.sleep(300);
            }catch(InterruptedException exp){
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

package ConcurrencyProblems.ProducerAndConsumer;

class Producer implements Runnable {
    private final Version2 sharedbuffer;

    public Producer(Version2 sharedbuffer){
        this.sharedbuffer=sharedbuffer;
    }

    public void run(){
        int value=1;
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

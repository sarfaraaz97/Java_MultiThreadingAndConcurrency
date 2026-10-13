package ConcurrencyProblems.ProducerAndConsumer;

class Consumer implements Runnable {
    private final Version2 sharedbuffer;

    public Consumer(Version2 sharedbuffer){
        this.sharedbuffer=sharedbuffer;
    }

    public void run(){
        for(int i=0;i<10;i++) {
            try {
                int item = sharedbuffer.consume();
                Thread.sleep(400);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}

import java.lang.Thread;
public class Producer extends Thread{
    private Counter counter;
    private Buffer queue;
    public Producer(int N,Counter counter, Buffer q){
        this.counter=counter;
        queue=q;
    }
    @Override
    public void run(){
        try{
            while(true){
                int c=counter.getandInc();
                queue.add(c);
                sleep(10);
            }
        }catch(InterruptedException ie){
            System.err.println("Producer interrupted!");
        }
    }
}
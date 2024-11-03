import java.lang.Thread;

public class Consumer extends Thread{
    private Buffer queue;
    public Consumer(Buffer queue){
        this.queue=queue;
    }
    @Override
    public void run(){
        try{
            while(true){
                int v=queue.remove(); 
                //System.out.println("Il valore estratto dal consumer è: "+ v);
                sleep(20);
                
            }
        }catch(InterruptedException ie){
            System.err.println("Consumer interrupted!");
        }
    }
}
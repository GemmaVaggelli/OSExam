
import java.util.concurrent.Semaphore;
import java.util.ArrayList;
public class ConsumerManager{
    private ArrayList<Consumer> consumers;
    private Semaphore mutex=new Semaphore(1);
    private Buffer queue;
    private int M;
    public ConsumerManager(int M,Buffer queue){
        this.queue=queue;
        this.M=M;
        consumers=new ArrayList<>();
    }
    public boolean checkAndStart() throws InterruptedException{
        boolean entered=false;
        mutex.acquire();
        if(queue.getSize()>(M*consumers.size())){
            System.out.println("Consumer started!");
            Consumer c=new Consumer(queue);
            consumers.add(c);
            c.start();
            entered=true;
        }
        mutex.release();
        return entered;
        
    }
    public int getQueueSize() throws InterruptedException{
        mutex.acquire();
        int size = queue.getSize();
        mutex.release();
        return size;
    }
    public void interrupt() throws InterruptedException{
        mutex.acquire();
        if(consumers.size()>2){
            System.out.println("Consumer interrupted by QM!");
            Consumer g;
            g=consumers.remove(0);
            g.interrupt();
        }
        mutex.release();
    }
    public void start()throws InterruptedException{
        mutex.acquire();
        Consumer c=new Consumer(queue);
        consumers.add(c);
        c.start();
        mutex.release();
    }
    public boolean isEmpty()throws InterruptedException{
        return queue.getSize()==0;
    }
    public void interruptAll(){
        for(int i=0;i<consumers.size();i++){
            consumers.remove(i).interrupt();
        }
    }

}
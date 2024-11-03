import java.util.concurrent.Semaphore;
public class Counter{
    private int counter;
    private Semaphore mutex=new Semaphore(1);
    public Counter(){
        this.counter=0;
    }
    public int getandInc() throws InterruptedException{
        mutex.acquire();
        int c=counter;
        counter++;
        mutex.release();
        return c;
    }
}
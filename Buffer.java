import java.util.concurrent.Semaphore;
import java.util.ArrayList;

public class Buffer{
    private ArrayList<Integer> queue;
    private Semaphore mutex=new Semaphore(1);
    private Semaphore pieni=new Semaphore(0);
    //private int numEl;
    public Buffer(){
        queue=new ArrayList<>();
        //numEl=0;
    }
    public void add(int value)throws InterruptedException{
        mutex.acquire();
        queue.add(value);
        mutex.release();
        pieni.release();
        //numEl++;
    }
    public int remove()throws InterruptedException{
        pieni.acquire();
        mutex.acquire();
        int r;
        r=queue.remove(0);
            //numEl--;
        mutex.release();
        return r;
    }
    public int getSize()throws InterruptedException{
        mutex.acquire();
        int v= queue.size();
        mutex.release();
        return v;
    }
}

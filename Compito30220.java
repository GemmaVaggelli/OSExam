public class Compito30220{
    public static void main(String[] args)throws InterruptedException{
        int M=2;
        int N=2;
        Counter co=new Counter();
        Buffer q=new Buffer();
        ConsumerManager cm=new ConsumerManager(M,q);
        QueueMonitor qm=new QueueMonitor(cm);
        qm.start();
        Producer[] p=new Producer[N];
        for(int i=0;i<p.length;i++){
            p[i]=new Producer(N, co, q);
            p[i].start();
            Thread.sleep(10000);
        }
        System.out.print("All producer started!");
        Thread.sleep(30000); 
        for(int i=0;i<p.length;i++){
            p[i].interrupt();
        }
        qm.setStop();
    }
}
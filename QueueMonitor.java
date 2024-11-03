import java.lang.Thread;
public class QueueMonitor extends Thread{
    private ConsumerManager cm;
    private int checks;
    private boolean stop;
    public QueueMonitor(ConsumerManager cm){
        this.cm=cm;
        checks=0;
        stop=false;
    }
    @Override
    public void run(){
        try{
            cm.start();
            long start=System.currentTimeMillis();
            while(!stop || !cm.isEmpty()){
                if(checks >= 3 ){
                    cm.interrupt(); //interrupt multiple consumer because QueueMonitor get 3 positive checks very quickly even if it restart every time from 0
                    checks=0;
                }
                else if(cm.getQueueSize() < 10){
                    checks++;
                }else{
                    checks=0;
                }
                long time=System.currentTimeMillis()-start;
                if(time>=5000){
                    if(cm.checkAndStart())
                        start=System.currentTimeMillis();
                }
            }
            cm.interruptAll();
        }catch(InterruptedException ie){
            System.err.println("QueueMonitor interrupted!");
        }
    }
    public void setStop(){
        stop=true;
    }

}
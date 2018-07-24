import java.util.concurrent.atomic.AtomicInteger;

public class JiOu {

    public static void main(String[] args){
        AtomicInteger a = new AtomicInteger(0);
        new JThread(a).start();
        new OThread(a).start();
    }

    static class JThread extends Thread{
        AtomicInteger a;

        public JThread(AtomicInteger a){
            this.a = a;
        }

        public void run(){
            while(a.get() < 100) {
                synchronized (a) {
                    if (a.get() % 2 == 0) {
                        try {
                            a.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        a.incrementAndGet();
                        System.out.println(Thread.currentThread().getId()+"--"+a.get());
                        a.notifyAll();
                    }
                }
            }
        }

    }

    static class OThread extends Thread{
        AtomicInteger a;

        public OThread(AtomicInteger a){
            this.a = a;
        }

        public void run(){
            while(a.get() < 100) {
                synchronized (a) {
                    if (a.get() % 2 == 1) {
                        try {
                            a.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        a.incrementAndGet();
                        System.out.println(Thread.currentThread().getId()+"--"+a.get());
                        a.notifyAll();
                    }
                }
            }
        }

    }
}

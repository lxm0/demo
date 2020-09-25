package learn.synchronize;

public class SynchronizedTest3 {
    public void methodOne(){
        System.out.println("Method One start");
        try {
            synchronized (this) {
                System.out.println("Method One execute");
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method One end");
    }

    public void methodTwo(){
        System.out.println("Method Two start");
        try {
            synchronized (this) {
                System.out.println("Method Two execute");
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method Two end");
    }

    public static void main(String[] args) {
        final SynchronizedTest test = new SynchronizedTest();
        //锁的是一个对象，所以同一个对象调用同步方法需要竞争锁。
        new Thread(new Runnable() {
            @Override
            public void run() {
                test.methodOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.methodTwo();
            }
        }).start();
    }
}

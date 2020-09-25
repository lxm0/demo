package learn.synchronize;

public class SynchronizedTest2 {
    public static synchronized void methodOne(){
        System.out.println("Method One start");
        try {
            System.out.println("Method One execute");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method One end");
    }

    public static synchronized void methodTwo(){
        System.out.println("Method Two start");
        try {
            System.out.println("Method Two execute");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method Two end");
    }

    public static void main(String[] args) {
        final SynchronizedTest test = new SynchronizedTest();
        final SynchronizedTest testTwo = new SynchronizedTest();
        // synchronized修饰静态方法，锁的是当前类，这个类的不同实例调用同步方法，需要竞争锁
        new Thread(new Runnable() {
            @Override
            public void run() {
                test.methodOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                testTwo.methodTwo();
            }
        }).start();
    }
}

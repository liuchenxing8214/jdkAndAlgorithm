package algorithm.test;

public class OuterClass {
    private static int sharedVariable = 0; // 静态变量

    static class InnerClass {
        public synchronized void increment() {
            sharedVariable++; // 增加共享变量
        }
    }

    public static void main(String[] args) {
        InnerClass inner = new InnerClass();

        // 创建多个线程来修改共享变量
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                inner.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                inner.increment();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("最终的共享变量值: " + sharedVariable);
    }
}

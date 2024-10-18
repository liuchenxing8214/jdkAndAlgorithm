package javaBase.os.ReentrantLock;

public class ReentrantLockExample {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(5); // 创建缓冲区，容量为5

        // 启动生产者线程
        Thread producer1 = new Thread(new Producer(buffer), "Producer 1");
        Thread producer2 = new Thread(new Producer(buffer), "Producer 2");

        // 启动消费者线程
        Thread consumer1 = new Thread(new Consumer(buffer), "Consumer 1");
        Thread consumer2 = new Thread(new Consumer(buffer), "Consumer 2");

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();

        try {
            producer1.join();
            producer2.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


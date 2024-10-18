package javaBase.os.ReentrantLock;

/**
 * 消费者类，模拟消费者线程
 */
public class Consumer implements Runnable {
    private final Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                Message message = buffer.take();
                Thread.sleep((int) (Math.random() * 150)); // 模拟消费时间
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

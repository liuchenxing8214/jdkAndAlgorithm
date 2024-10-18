package javaBase.os.ReentrantLock;

/**
 * 生产者类，模拟生产者线程
 */
public class Producer implements Runnable {
    private final Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                Message message = new Message("Message " + i);
                buffer.put(message);
                Thread.sleep((int) (Math.random() * 100)); // 模拟生产时间
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


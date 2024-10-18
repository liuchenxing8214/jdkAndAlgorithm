package javaBase.os.monitor;

// 消费者类
public class Consumer implements Runnable {
    private final ProducerConsumer pc;

    public Consumer(ProducerConsumer pc) {
        this.pc = pc;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 20; i++) {
                Message message = new Message();
                message.setName(Thread.currentThread().getName());
                pc.consume(message);
                Thread.sleep(150); // 模拟消费延迟
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
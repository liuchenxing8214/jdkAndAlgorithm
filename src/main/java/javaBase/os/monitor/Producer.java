package javaBase.os.monitor;

// 生产者类
class Producer implements Runnable {
    private final ProducerConsumer pc;

    public Producer(ProducerConsumer pc) {
        this.pc = pc;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 3; i++) {
                Message message = new Message();
                message.setName(Thread.currentThread().getName());
                message.setContent("消息体内容为" + Counter.incrementAndGet());
                pc.produce(message);
                Thread.sleep(100); // 模拟生产延迟
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}

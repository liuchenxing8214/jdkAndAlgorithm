package javaBase.os.monitor;

import java.util.LinkedList;
import java.util.Queue;


// 管程类
public class ProducerConsumer<T> {
    private final Queue<Message> queue = new LinkedList<>();
    private final int LIMIT = 5; // 队列的最大容量

    // 生产者方法
    public synchronized void produce(Message t) throws InterruptedException {
        while (queue.size() == LIMIT) {
            wait(); // 队列满，等待消费者消费
        }
        queue.add(t);
        System.out.println("生产者"+t.getName()+"生产: " + t.getContent());
        notifyAll(); // 通知消费者可以消费
    }

    // 消费者方法
    public synchronized String consume(Message t) throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // 队列空，等待生产者生产
        }
        Message message = queue.poll();
        System.out.println("消费者"+t.getName()+"消费: " + message.getContent());
        notifyAll(); // 通知生产者可以生产
        return message.getContent();
    }
}

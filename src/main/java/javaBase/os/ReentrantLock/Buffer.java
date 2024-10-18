package javaBase.os.ReentrantLock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 共享缓冲区类
 */
public class Buffer {
    private final Queue<Message> queue = new LinkedList<>();
    private final int capacity;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public Buffer(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 生产者放入消息
     * @param message 放入的消息对象
     * @throws InterruptedException
     */
    public void put(Message message) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                notFull.await(); // 等待缓冲区未满
            }
            queue.add(message);
            System.out.println(Thread.currentThread().getName() + " produced " + message);
            notEmpty.signalAll(); // 通知消费者缓冲区不为空
        } finally {
            lock.unlock();
        }
    }

    /**
     * 消费者取出消息
     * @return 取出的消息对象
     * @throws InterruptedException
     */
    public Message take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await(); // 等待缓冲区不为空
            }
            Message message = queue.poll();
            System.out.println(Thread.currentThread().getName() + " consumed " + message);
            notFull.signalAll(); // 通知生产者缓冲区未满
            return message;
        } finally {
            lock.unlock();
        }
    }
}

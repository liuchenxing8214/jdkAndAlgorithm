package algorithm.test;

import java.util.PriorityQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafePriorityQueue {
    private final PriorityQueue<Integer> priorityQueue;
    private final ReentrantLock lock;

    public ThreadSafePriorityQueue() {
        this.priorityQueue = new PriorityQueue<>();
        this.lock = new ReentrantLock();
    }

    /*    public void add(int value) {
            lock.lock();
            try {
                priorityQueue.offer(value);
            } finally {
                lock.unlock();
            }
        }

        public Integer poll() {
            lock.lock();
            try {
                return priorityQueue.poll();
            } finally {
                lock.unlock();
            }
        }*/

    public void add(int value) {
        priorityQueue.offer(value);
    }

    public Integer poll() {
        return priorityQueue.poll();
    }


    public static void main(String[] args) {
        ThreadSafePriorityQueue safeQueue = new ThreadSafePriorityQueue();

        // 示例线程添加元素
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                safeQueue.add(i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 100; i < 200; i++) {
                safeQueue.add(i);
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

        // 从队列中取出元素
        while (true) {
            Integer value = safeQueue.poll();
            if (value == null) break;
            System.out.println(value);
        }
    }
}

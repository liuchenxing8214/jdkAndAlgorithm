package algorithm.test;

import java.util.PriorityQueue;

public class PriorityQueueThreadUnsafeExample {
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        // 创建多个线程同时访问同一个 PriorityQueue
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                priorityQueue.offer(i);
                System.out.println("Thread-1 added: " + i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 10; i < 20; i++) {
                priorityQueue.offer(i);
                System.out.println("Thread-2 added: " + i);
            }
        });

        thread1.start();
        thread2.start();

        // 等待线程执行完毕
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 输出 PriorityQueue 的内容
        System.out.println("PriorityQueue contents: " + priorityQueue);
    }
}


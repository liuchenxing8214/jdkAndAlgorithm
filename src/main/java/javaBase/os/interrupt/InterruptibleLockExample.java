package javaBase.os.interrupt;

import java.util.concurrent.locks.ReentrantLock;

public class InterruptibleLockExample {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                // 尝试获取锁，允许中断
                lock.lockInterruptibly();
                try {
                    System.out.println("Thread 1: Lock acquired");
                    // 模拟长时间的任务
                    System.out.println("线程1模拟等待任务开始");
                    Thread.sleep(5000);
                    System.out.println("线程1正在等待中");
                } finally {
                    lock.unlock();
                    System.out.println("Thread 1: Lock released");
                }
            } catch (InterruptedException e) {
                System.out.println("Thread 1: Interrupted while waiting for lock");
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                // 尝试获取锁，允许中断
                lock.lockInterruptibly();
                try {
                    System.out.println("Thread 2: Lock acquired");
                } finally {
                    lock.unlock();
                    System.out.println("Thread 2: Lock released");
                }
            } catch (InterruptedException e) {
                System.out.println("Thread 2: Interrupted while waiting for lock");
            }
        });

        thread1.start();
        // 确保 thread1 先获取锁
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();

        // 中断 thread1
        thread1.interrupt();
    }
}

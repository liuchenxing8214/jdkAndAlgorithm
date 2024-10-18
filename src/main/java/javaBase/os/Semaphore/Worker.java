package javaBase.os.Semaphore;

import java.util.concurrent.Semaphore;

class Worker implements Runnable {
    private final Semaphore semaphore;

    Worker(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();  // Acquire a permit
            System.out.println(Thread.currentThread().getName() + " is working.");
            Thread.sleep((long) (Math.random() * 1000));  // Simulate work
            System.out.println(Thread.currentThread().getName() + " has finished.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();  // Release the permit
        }
    }
}

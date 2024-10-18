package javaBase.os.Semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);  // Three permits

        for (int i = 0; i < 10; i++) {
            new Thread(new Worker(semaphore)).start();
        }
    }
}

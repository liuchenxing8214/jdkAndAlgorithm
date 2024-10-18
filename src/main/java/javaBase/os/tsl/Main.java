package javaBase.os.tsl;

public class Main {
    public static void main(String[] args) {
        TSLLock lock = new TSLLock();
        Thread thread1 = new Thread(new CriticalSection(lock), "Thread 1");
        Thread thread2 = new Thread(new CriticalSection(lock), "Thread 2");
        Thread thread3 = new Thread(new CriticalSection(lock), "Thread 3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}


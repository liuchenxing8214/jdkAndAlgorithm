package javaBase.os.tsl;

class CriticalSection implements Runnable {
    private TSLLock lock;
    public CriticalSection(TSLLock lock) {
        this.lock = lock;
    }
    @Override
    public void run() {
        lock.acquireLock(); // 获取锁
        try {
            // 访问临界资源
            System.out.println(Thread.currentThread().getName() + " is in the critical section.");
            // 模拟临界区的处理
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.releaseLock(); // 释放锁
            System.out.println(Thread.currentThread().getName() + " has left the critical section.");
        }
    }
}


package javaBase.os.swap;

public class SwapLock {
    private volatile int lock = 0; // 锁变量，0表示未占用，1表示已占用
    // 获取锁
    public void acquireLock() {
        while (true) {
            // 尝试获取锁
            int currentLock = lock; // 读取当前锁状态
            if (currentLock == 0 && compareAndSetLock(0, 1)) {
                return; // 成功获取锁
            }
            // 锁已被占用，忙等待
        }
    }
    // 释放锁
    public void releaseLock() {
        lock = 0; // 解锁
    }
    // 原子比较并设置锁的状态
    private synchronized boolean compareAndSetLock(int expected, int newValue) {
        if (lock == expected) {
            lock = newValue;
            return true;
        }
        return false;
    }
}

package javaBase.os.tsl;

public class TSLLock {
    private volatile boolean lock = false; // 锁变量
    // 获取锁
    public void acquireLock() {
        while (true) {
            // 尝试获取锁
            if (!lock) {
                // 如果锁未被占用，设置为占用
                lock = true;
                return; // 成功获取锁
            }
            // 锁已被占用，忙等待
        }
    }
    // 释放锁
    public void releaseLock() {
        lock = false; // 解锁
    }
}

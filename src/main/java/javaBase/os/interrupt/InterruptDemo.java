package javaBase.os.interrupt;

public class InterruptDemo {
    public static void main(String[] args) {
        Thread workerThread = new Thread(() -> {
            try {
                while (true) {
                    // 模拟工作
                    System.out.println("工作中...");
                    Thread.sleep(1000); // 模拟耗时操作
                }
            } catch (InterruptedException e) {
                // 捕获中断异常，进行清理
                System.out.println("线程被中断，进行清理操作。");
            } finally {
                // 释放资源
                System.out.println("资源已释放。");
            }
        });

        workerThread.start();

        // 主线程等待一段时间后中断工作线程
        try {
            Thread.sleep(3000); // 主线程等待3秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 中断工作线程
        workerThread.interrupt();
    }
}

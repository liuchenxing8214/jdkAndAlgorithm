package javaBase.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceDemo {
    public static void main(String[] args) {
        ReferenceQueue<Resource> referenceQueue = new ReferenceQueue<>();
        Resource resource = new Resource();

        // 创建虚引用
        PhantomReference<Resource> phantomReference = new PhantomReference<>(resource, referenceQueue);

        // 清空强引用
        resource = null;

        // 强制进行垃圾回收
        System.gc();

        // 等待一段时间，确保垃圾回收完成
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 检查引用队列
        if (referenceQueue.poll() != null) {
            System.out.println("Resource has been collected.");
            // 在这里可以执行清理操作
            System.out.println("Performing cleanup...");
        } else {
            System.out.println("Resource is still alive.");
        }
    }
}

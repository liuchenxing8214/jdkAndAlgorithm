package javaBase.reference02;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
public class SelfLoopNode {
    SelfLoopNode next;
    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<SelfLoopNode> referenceQueue = new ReferenceQueue<>();
        SelfLoopNode node = new SelfLoopNode();
        node.next = node; // 自循环
        PhantomReference<SelfLoopNode> phantomReference = new PhantomReference<>(node, referenceQueue);
        node = null; // 取消对 node 的强引用
        System.gc(); // 请求垃圾回收
// 等待引用被放入引用队列
        if (referenceQueue.remove(1000) != null) {
            System.out.println("节点已被垃圾回收");
        } else {
            System.out.println("节点未被垃圾回收");
        }
    }
}


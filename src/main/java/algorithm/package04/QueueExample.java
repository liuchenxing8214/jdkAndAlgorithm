package algorithm.package04;

import java.util.LinkedList;
import java.util.Queue;

public class QueueExample {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        // 添加元素到队列尾部
        queue.offer("Java");
        queue.offer("Python");
        queue.offer("C++");

        // 输出队列中的元素
        System.out.println(queue); // 输出 [Java, Python, C++]

        String poll = queue.poll();
        System.out.println("poll:"+poll);
        System.out.println(queue);

    }
}

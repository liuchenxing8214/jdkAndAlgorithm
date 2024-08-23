package algorithm.package04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 两个队列实现栈
 */
public class TwoQueueComposeStack_07 {
    public static class MyQueue<T> {
        private Queue<T> queue;
        private Queue<T> help;

        public MyQueue() {
            this.queue = new LinkedList<>();
            this.help = new LinkedList<>();
        }

        //从栈顶弹出数据
        public T poll() {
            //(I)将前n-1个数据挪到help中,将最后一个数据放在queue并返回
            while (queue.size() > 1) {
                help.add(queue.poll());
            }
            T value = queue.poll();
            //(II)queue和help两个区域引用换一下,即是queue和help互换
            Queue<T> temp = queue;
            queue = help;
            help = temp;
            return value;
        }

        //从栈顶增加数据
        public void push(T value) {
            queue.add(value);
        }

        public T peek() {
            //(I)将前n-1个数据挪到help中,将最后一个数据放在queue并返回
            while (queue.size() > 1) {
                help.add(queue.poll());
            }
            T value = queue.poll();
            //(II)queue和help两个区域引用换一下,即是queue和help互换
            Queue<T> temp = queue;
            queue = help;
            help = temp;
            queue.add(value);
            return value;
        }
        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }
    public static void main(String[] args) {
        System.out.println("test begin");
        MyQueue<Integer> myStack = new MyQueue<>();
        Stack<Integer> test = new Stack<>();
        int testTime = 1000000;
        int max = 1000000;
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!test.isEmpty()) {
                    System.out.println("Oops");
                }
                int num = (int) (Math.random() * max);
                myStack.push(num);
                test.push(num);
            } else {
                if (Math.random() < 0.25) {
                    int num = (int) (Math.random() * max);
                    myStack.push(num);
                    test.push(num);
                } else if (Math.random() < 0.5) {
                    if (!myStack.peek().equals(test.peek())) {
                        System.out.println("Oops");
                    }
                } else if (Math.random() < 0.75) {
                    if (!myStack.poll().equals(test.pop())) {
                        System.out.println("Oops");
                    }
                } else {
                    if (myStack.isEmpty() != test.isEmpty()) {
                        System.out.println("Oops");
                    }
                }
            }
        }

        System.out.println("test finish!");

    }
}

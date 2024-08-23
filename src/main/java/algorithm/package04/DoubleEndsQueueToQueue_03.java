package algorithm.package04;


import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 双端链表实现队列结构【加元素是从尾巴上加,弹出元素是从头部上弹出来】
 * 双端链表实现栈结构【加元素是从尾巴上加,弹出元素也是从尾巴上弹出来】
 */
public class DoubleEndsQueueToQueue_03 {

    @Data
    public static class Node<T> {
        private T value;
        private Node<T> last;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    public static class DoubleEndsQueue<T> {
        private Node<T> head;
        private Node<T> tail;

        public void addFromBottom(T value) {
            Node<T> cur = new Node<>(value);
            //双向链表为空时,表示没有数据存放到节点中
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                //如果双向链表中有数据,
                //(I)尾节点的下一个节点指向新添加的节点
                //(II)新添加的节点的上一个节点指向尾巴节点
                //(III)尾巴节点指向当前节点
                tail.next = cur;
                cur.last = tail;
                tail = cur;
            }
        }

        public T popFromHead() {
            if (head == null) {
                return null;
            }
            //假如双向链表中只有一个节点时
            Node<T> cur = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                //将头节点的下个节点作为头节点,所有与头节点的关联都需要断开
                head = head.next;
                cur.next = null;
                head.last = null;
            }
            return cur.getValue();
        }

        public T popFromBottom() {
            if (head == null) {
                return null;
            }
            //假如双向链表中只有一个节点时
            Node<T> cur = tail;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                //将尾节点的上个节点作为尾节点,所有与尾节点的关联都需要断开
                tail = tail.last;
                tail.next = null;
                cur.last = null;
            }
            return cur.getValue();
        }


        public boolean isEmpty() {
            return head == null;
        }

    }

    public static class MyQueue<T> {
        private DoubleEndsQueue<T> queue;

        public MyQueue() {
            queue = new DoubleEndsQueue<T>();
        }

        public void push(T value) {
            queue.addFromBottom(value);
        }

        public T poll() {
            return queue.popFromHead();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

    }

    public static class MyStack<T> {
        private DoubleEndsQueue<T> queue;

        public MyStack() {
            queue = new DoubleEndsQueue<T>();
        }

        public void push(T value) {
            queue.addFromBottom(value);
        }

        public T pop() {
            return queue.popFromBottom();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    public static boolean isEqual(Integer o1, Integer o2) {
        if (o1 == null && o2 != null) {
            return false;
        }
        if (o1 != null && o2 == null) {
            return false;
        }
        if (o1 == null && o2 == null) {
            return true;
        }
        return o1.equals(o2);
    }

    public static void main(String[] args) {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            MyStack<Integer> myStack = new MyStack<>();
            MyQueue<Integer> myQueue = new MyQueue<>();
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum; j++) {
                int nums = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myStack.push(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {
                        myStack.push(nums);
                        stack.push(nums);
                    } else {
                        if (!isEqual(myStack.pop(), stack.pop())) {
                            System.out.println("oops!");
                        }
                    }
                }
                int numq = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myQueue.push(numq);
                    queue.offer(numq);
                } else {
                    if (Math.random() < 0.5) {
                        myQueue.push(numq);
                        queue.offer(numq);
                    } else {
                        if (!isEqual(myQueue.poll(), queue.poll())) {
                            System.out.println("oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }

}

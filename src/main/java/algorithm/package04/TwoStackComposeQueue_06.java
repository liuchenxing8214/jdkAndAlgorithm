package algorithm.package04;

import java.util.Stack;

/**
 * 两个栈实现队列
 */
public class TwoStackComposeQueue_06 {
    public static class MyQueue {
        private Stack<Integer> pushStack;  //原则一:pushStack导数据到popStack时,要一次性导完成。
        private Stack<Integer> popStack;   //原则二:popStack的只要有数据时,pushStack是不能导数据到popStack的

        public MyQueue() {
            this.pushStack = new Stack<>();
            this.popStack = new Stack<>();
        }

        //队列从右边弹出
        public int poll() {
            if (popStack == null && pushStack == null) {
                throw new RuntimeException("队列中没有数据,无法弹出数据");
            }
            pushStackToPopStack();
            return popStack.pop();
        }


        public int peek() {
            if (popStack == null && pushStack == null) {
                throw new RuntimeException("队列中没有数据,无法弹出数据");
            }
            pushStackToPopStack();
            return popStack.peek();
        }

        public void add(int newValue) {
            pushStack.push(newValue);
            pushStackToPopStack();
        }


        private void pushStackToPopStack() {
            if (popStack.isEmpty()) {
                //pushStack可以向popStack导入数据了,而且要一次性导入完成
                while (!pushStack.isEmpty()) {
                    popStack.push(pushStack.pop());
                }
            }
        }
    }

    public static void main(String[] args) {
        MyQueue test = new MyQueue();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }
}

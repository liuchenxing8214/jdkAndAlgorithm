package algorithm.package04;


import java.util.Stack;

/**
 * 在栈的基本功能上实现一个特殊的栈,
 * 能将栈中最小元素返回
 */
public class GetMinStack_05 {
    public static class MyStack {
        private Stack<Integer> dataStack;  //正常的数据栈
        private Stack<Integer> minStack;   //数据发生更新时,实时更新最小值的栈结构

        public MyStack() {
            this.dataStack = new Stack<>();
            this.minStack = new Stack<>();
        }

        public void push(int value) {
            if (dataStack.isEmpty()) {
                minStack.push(value);
            } else if (value >= getMin()) {
                //如果新加的值或者等于,
                // 还是将之前的最小值加入到最小栈结构中
                minStack.push(getMin());
            } else {
                //否则新加的数据小于最小栈中最小的数据,
                //将新加的数据直接放到最小栈结构中
                minStack.push(value);
            }
            dataStack.push(value);
        }

        public int pop() {
            if (dataStack.isEmpty()) {
                throw new RuntimeException("栈中的数据为空,并能弹出数据");
            }
            int value = dataStack.pop();
            minStack.pop();
            return value;
        }


        public int getMin() {
            return minStack.peek();
        }
    }

    public static void main(String[] args) {
        MyStack stack2 = new MyStack();
        stack2.push(3);
        System.out.println(stack2.getMin());
        stack2.push(4);
        System.out.println(stack2.getMin());
        stack2.push(1);
        System.out.println(stack2.getMin());

        System.out.println(stack2.pop());
        System.out.println(stack2.getMin());
    }
}

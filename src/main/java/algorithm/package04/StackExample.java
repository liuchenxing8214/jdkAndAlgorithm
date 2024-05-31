package algorithm.package04;

import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);

        int topElement = stack.peek();
        System.out.println("栈顶元素的值是:" + topElement);
        System.out.println("最终的栈为:"+stack);
    }
}
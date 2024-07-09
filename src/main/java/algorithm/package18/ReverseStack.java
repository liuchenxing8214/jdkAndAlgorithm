package algorithm.package18;

import java.util.Stack;

public class ReverseStack {
    public static void main(String[] args) {
        Stack<Integer> stack  = new Stack<>();
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        System.out.println(stack);
        reverseStack(stack);
        System.out.println(stack);
    }


    public static void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = f(stack);
        reverseStack(stack);
        stack.push(i);
    }

    //栈底元素移除掉
    //上面的元素盖下来
    // 返回移除掉的栈底元素
    public static int f(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }

}

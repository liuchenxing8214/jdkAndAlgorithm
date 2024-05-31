package jvm.stack;

/**
 * @Auther: csp1999
 * @Date: 2020/11/10/11:36
 * @Description: 演示栈帧
 */
public class Demo01 {
    public static void main(String[] args) {
        methodA();
    }

    private static void methodA() {
        methodB(1, 2);
    }

    private static int methodB(int a, int b) {
        int c = a + b;
        return c;
    }
}


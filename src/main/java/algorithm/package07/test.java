package algorithm.package07;

public class test {
    public static void main(String[] args) {
        int a = (int) (Math.random() * 100);
        System.out.println("调函数之前a==="+a);
        print(a++);
        System.out.println("b====" + a);


        int b = (int) (Math.random() * 100);
        System.out.println("调函数之前b==="+b);
        print1(--b);
        System.out.println("b====" + b);

    }

    public static void print(int a) {
        System.out.println("函数中a===" + a);
    }

    public static void print1(int b) {
        System.out.println("函数中b===" + b);
    }


}

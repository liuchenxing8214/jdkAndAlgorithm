package algorithm.package07;

public class test {
    public static void main(String[] args) {
        int a = (int) (Math.random() * 100);
        System.out.println("调函数之前a==="+a);
        print(a++);
        System.out.println("b====" + a);

    }

    public static void print(int a) {
        System.out.println("函数中a===" + a);
    }

}

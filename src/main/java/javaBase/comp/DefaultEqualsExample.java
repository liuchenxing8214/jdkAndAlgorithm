package javaBase.comp;

public class DefaultEqualsExample {
    public static void main(String[] args) {
        String str1 = new String("Hello");
        String str2 = new String("Hello");
        System.out.println(str1.equals(str2)); // 输出 true，因为内容相同
        System.out.println(str1 == str2);      // 输出 false，因为引用不同
    }
}
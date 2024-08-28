package algorithm.test;

public class BitwiseShift {
    public static void main(String[] args) {
        int a = -8;
        int arithmeticShift = a >> 2; // 算术右移
        int logicalShift = a >>> 2;    // 逻辑右移

        System.out.println("Arithmetic Right Shift: " + arithmeticShift);
        System.out.println("Logical Right Shift: " + logicalShift);
    }
}

package algorithm.test;

public class Test01 {
    public static void main(String[] args) {
        System.out.println(getRightFirstOneNum(7056));
    }

    public static int getRightFirstOneNum(int num) {
        return num & (~num + 1);
        //return num & (-num);
    }
}

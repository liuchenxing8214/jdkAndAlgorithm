package jdkSeven;

public class Test02 {
    public static void main(String[] args) {
        int sshift = 0;   //2的幂数   记录偏移量
        int ssize = 1;  //2的n次方 记录大于等于concurrencyLevel的最小2的幂的数据=16
        int concurrencyLevel = 16;
        while (ssize < concurrencyLevel) {
            ++sshift;
            ssize <<= 1;
        }
        System.out.println("ssize="+ssize);
        System.out.println("sshift="+sshift);
        int i = 3;
        if(i>2)
            ++i;
        System.out.println(i);

    }
}

package jdkRight.package01;

public class test02 {
    public static void main(String[] args) {
        String[] s1={"1","2","3","4","5"};
        String[] s2={"6","7","8","9","10"};
        for (int i = 0; i < s1.length; i++) {
            System.out.println("外层循环执行i="+s1[i]);
            for (int j = 0; j < s2.length; j++) {
                System.out.println("内层循环执行j="+s2[j]);
                if (j > 2) {
                    System.out.println("使用return退出循环");
                    return;
                }
                System.out.println(s1[i]+"============================="+s2[j]);
            }
        }


    }


}

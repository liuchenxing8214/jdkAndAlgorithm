package algorithm.test;

public class XinParam {

    public static void main(String[] args) {
        Integer i = new Integer(0);
        getNum(i);
        System.out.println(i);
    }


    public static void getNum(Integer i) {
        Integer a = new Integer(3);
        i = a;
    }
}



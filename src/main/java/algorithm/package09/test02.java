package algorithm.package09;

import java.math.BigDecimal;

public class test02 {

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("1.00");
        BigDecimal b = new BigDecimal("1");
        System.out.println(a.compareTo(b));
        System.out.println(a.equals(b));

    }

    public static int getValue(int i){
        int result =0;
        switch (i){
            case 1:
                result =result+i;
            case 2:
                result = result+i*2;
            case 3:
                result = result+i*3;
        }
        return result;
    }

}
